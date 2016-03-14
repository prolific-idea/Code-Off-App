package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.domain.entities.Person;
import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;
import com.prolificidea.templates.tsw.services.DTOs.EntryDTO;
import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;
import com.prolificidea.templates.tsw.services.DTOs.TechnologyDTO;
import com.prolificidea.templates.tsw.services.providers.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PersonAndEntryFactoryImpl implements PersonAndEntryFactory {


    @Autowired
    PersonService personService;

    @Autowired
    EntryService entryService;

    @Autowired
    ChallengeService challengeService;

    @Autowired
    UrlServiceImpl urlService;

    @Autowired
    ScoreService scoreService;

    @Autowired
    ExtensionExtractor extensionExtractor;

    private final static String API_URL = "https://api.github.com/repos/";

    private ChallengeDTO challenge;

    private RestTemplate restCall = new RestTemplate();

    public String markSolutionsOfUserIfTheyExsistForAChallenge(ChallengeDTO pollingChallenge) throws JSONException {

        challenge = pollingChallenge;
        String URLForks = API_URL + challenge.getUrl() + "/forks";
        JSONArray forks = getJSONFromURL(URLForks);
        String userRepoURL = "End";
        for (int forkNumber = 0; forkNumber < forks.length(); forkNumber++) {
            JSONObject fork = forks.getJSONObject(forkNumber);
            CreatePeopleAndEntries(fork);
        }
        return userRepoURL;
    }

    private void CreatePeopleAndEntries(JSONObject fork) throws JSONException {
        PersonDTO person = new PersonDTO();
        List<EntryDTO> entries = new ArrayList<EntryDTO>();
        buildPerson(person, entries, fork);
        buildEntries(person, entries, fork);
        int newPersonID = createPerson(person);
        if (entries.size() > 0) {

            createEntries(entries, newPersonID);
        }

    }

    private void buildEntries(PersonDTO person, List<EntryDTO> entries, JSONObject fork) throws JSONException {
        String branchesUrl = person.getRepoUrl() + "/branches";
        JSONArray branches = getJSONFromURL(branchesUrl);
        for (int branchNumber = 0; branchNumber < branches.length(); branchNumber++) {
            JSONObject branch = branches.getJSONObject(branchNumber);
            EntryDTO entry = new EntryDTO();
            entry.setBranch(branch.getString("name"));
            entry.setFullName(fork.getString("full_name"));
            entry.setUrl(person.getRepoUrl() + "/branches/" + entry.getBranch());

            EntryDTO validEntry = createAndCheckEntry(entry);

            if (validEntry != null) {
                TechnologyDTO tech = setTech(entry.getBranch(), person.getRepoUrl(),entry);
                if (tech != null) {
                    entry.setTechId(tech.getTechId());
                    entries.add(validEntry);
                }
            }
        }
    }

    private EntryDTO createAndCheckEntry(EntryDTO entry) {
        boolean valid = checkSolutionExists(entry);
        if (valid)// need validaty checker
        {
            setEntry(entry);
        } else {
            entry = null;
        }

        return entry;
    }

    private boolean checkSolutionExists(EntryDTO entry) {
        urlService.setOwnerRepoBranchFile(entry.getFullName(), entry.getBranch(), challenge.getSolutionFilePath());
        String fileSolution = urlService.getContent();
        if (fileSolution.equals(""))// nothing is returned if there was a n error in that function
            return false;

        updateEntrySolution(entry, fileSolution);

        boolean isCorrect = urlService.compareSolution(fileSolution, challenge.getChallengeId());
        if (isCorrect) {
            entry.setResult(2);
        } else {
            entry.setResult(1);
        }
        return true;
    }

    private void updateEntrySolution(EntryDTO entry, String fileSolution) {
        byte[] solutionInBytes = fileSolution.getBytes();
        entry.setSolution(solutionInBytes);
    }

    private void setEntry(EntryDTO entry) {
        entry.setChallengeId(challenge.getChallengeId());
        entry.setDate(new Date());
    }

    private TechnologyDTO setTech(String branch, String URL, EntryDTO entry) throws JSONException {
        String path = getPath();
        URL = URL + "/contents/" + path + "?ref=" + branch;

        return TraverseFileDirectory(URL);
        /*TechnologyDTO technology = null;
        JSONArray contents = getJSONFromURL(URL);
        if (contents == null) {
            return technology;
        }
        for (int contentNumber = 0; contentNumber < contents.length(); contentNumber++) {
            JSONObject content = contents.getJSONObject(contentNumber);
            technology = extensionExtractor.extractExtension(content.getString("name"));
            if (technology != null)
                return technology;
        }
        return technology;*/

    }

    private String getPath() {
        String solutionPath = challenge.getSolutionFilePath();
        String[] splicedSolutionPath = solutionPath.split("/");
        StringBuilder builder = new StringBuilder();
        for (int numberOfString = 0; numberOfString < splicedSolutionPath.length - 1; numberOfString++) {
            builder.append(splicedSolutionPath[numberOfString]);
        }
        return builder.toString();
    }


    private void createEntries(List<EntryDTO> entries, int newPersonID) {
        for (EntryDTO entry : entries) {
            List<EntryDTO> entryExsists = entryService.searchEntrys("url", entry.getUrl());
            if (entryExsists.size() > 0) {
                EntryDTO oldEntry = entryExsists.get(0);
                if (oldEntry.getResult() != entry.getResult()) {
                    oldEntry.setResult(entry.getResult());
                    EntryDTO storeEntry = entryService.updateEntry(oldEntry);
                    scoreService.addScore(storeEntry);
                }

            } else {
                entry.setPersonId(newPersonID);
                EntryDTO createdEntry = entryService.createEntry(entry);
                scoreService.addScore(createdEntry);
            }
        }
    }

    private int createPerson(PersonDTO person) {
        List<PersonDTO> personList = personService.searchPersons("username", person.getUsername());
        if (personList.size() > 0) {
            return personList.get(0).getPersonId();
        }
        PersonDTO newPerson = personService.createPerson(person);
        return newPerson.getPersonId();
    }

    private void buildPerson(PersonDTO person, List<EntryDTO> entries, JSONObject fork) throws JSONException {

        person.setScore(0);
        person.setUsername(getUsername(fork));
        person.setUrl("https://github.com/" + person.getUsername());
        //person.setUrl(getUserURLFromFork(fork));
        person.setRepoUrl(buildRepoURL(fork));
        setFirstAndLastNames(person);
    }

    private void setFirstAndLastNames(PersonDTO person) throws JSONException {
        String URL = "https://api.github.com/users/" + person.getUsername();
        JSONObject user = getJSONObjectFromURL(URL);
        if (user == null)
            return;
        String firstAndSecondName = user.getString("name");
        if (firstAndSecondName == null)
            return;

        String[] firstAndSecondNameSeperated = firstAndSecondName.split(" ");

        if (firstAndSecondNameSeperated.length > 0 && !(firstAndSecondNameSeperated[0].equals("null"))) {
            person.setFirstName(firstAndSecondNameSeperated[0]);
            if (firstAndSecondNameSeperated.length > 1) {
                String surname = "";
                for (int namePosition = 1; namePosition < firstAndSecondNameSeperated.length; namePosition++) {
                    surname += firstAndSecondNameSeperated[namePosition] + " ";
                }
                person.setLastName(surname.trim());
            }
        }
    }

    private String getUserURLFromFork(JSONObject fork) throws JSONException {
        JSONObject owner = fork.getJSONObject("owner");
        return owner.getString("url");
    }

    private String buildRepoURL(JSONObject fork) throws JSONException {
        return "https://api.github.com/repos/" + fork.get("full_name");
        // https://api.github.com/repos/{full_name}/
    }

    private String getUsername(JSONObject fork) throws JSONException {
        JSONObject owner = fork.getJSONObject("owner");
        String userName = owner.getString("login");
        return userName;
    }

   private TechnologyDTO TraverseFileDirectory(String URL) throws JSONException {
        JSONArray directoryContent = getJSONFromURL(URL);

        if (directoryContent == null)
            return null;

        for (int contentNumber = 0; contentNumber < directoryContent.length(); contentNumber++) {

            JSONObject content = directoryContent.getJSONObject(contentNumber);
            String name = content.getString("name");
            TechnologyDTO technology = extensionExtractor.extractExtension(name);
            if (technology != null) {
                if (technology.getDescription().equals("Directory")) {
                    String directory = content.getString("url");
                    TechnologyDTO returnTech = TraverseFileDirectory(directory);
                    if (returnTech != null)
                    {
                        return returnTech;
                    }

                } else {

                    return technology;
                }
            }
        }
        return null;
    }

/*    private EntryDTO TraverseFileDirectory(String URL, EntryDTO entry) throws JSONException {
        JSONArray directoryContent = getJSONFromURL(URL);

        if (directoryContent == null)
            return null;

        for (int contentNumber = 0; contentNumber < directoryContent.length(); contentNumber++) {

            JSONObject content = directoryContent.getJSONObject(contentNumber);
            String name = content.getString("name");
            TechnologyDTO technology = extensionExtractor.extractExtension(name);
            if (technology != null) {
                if (technology.getDescription().equals("Directory")) {
                    String directory = content.getString("url");
                    entry = TraverseFileDirectory(directory,entry);
                    if (entry.getTechId() != 0)
                    {
                        return entry;
                    }

                } else {
                    entry.setTechId(technology.getTechId());
                    return entry;
                }
            }
        }
        return null;
    }*/

    private JSONArray getJSONFromURL(String URL) {
        URL = URL.replace("%20"," ");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic YjE0NTY4NzdAdHJidm4uY29tOkVudEFsbFN0YXJSZWRvbmVBbGxBcXVpcmVk");// obtained with post mans ecyption with given username and password
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> results = restCall.exchange(
                URL,
                HttpMethod.GET, entity, String.class);

        try {
            JSONArray jsonResults = new JSONArray(results.getBody());
            return jsonResults;
        } catch (JSONException e) {
            return null;
        }
    }

    private JSONObject getJSONObjectFromURL(String URL) {
        URL = URL.replace("%20"," ");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic YjE0NTY4NzdAdHJidm4uY29tOkVudEFsbFN0YXJSZWRvbmVBbGxBcXVpcmVk");// obtained with post mans ecyption with given username and password
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> results = restCall.exchange(
                URL,
                HttpMethod.GET, entity, String.class);

        try {
            JSONObject jsonResults = new JSONObject(results.getBody());
            return jsonResults;
        } catch (JSONException e) {
            return null;
        }
    }


}

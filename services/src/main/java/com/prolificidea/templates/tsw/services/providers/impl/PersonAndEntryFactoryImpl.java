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
import org.springframework.transaction.annotation.Transactional;
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

    public String markSolutionsOfUserIfTheyExsistForAChallenge(ChallengeDTO pollingChallenge) throws JSONException {

        challenge = pollingChallenge;
        String URLForks = API_URL + challenge.getUrl() + "/forks";
        JSONArray forks = getJSONFromURL(URLForks);

        for (int forkNumber = 0; forkNumber < forks.length(); forkNumber++) {
            JSONObject fork = forks.getJSONObject(forkNumber);
            CreatePeopleAndEntries(fork);
        }

        return "finished";
    }

    private void CreatePeopleAndEntries(JSONObject fork) throws JSONException {
        PersonDTO person = new PersonDTO();
        List<EntryDTO> entries = new ArrayList<EntryDTO>();

        buildPerson(person, entries, fork);
        buildEntries(person, entries, fork);

        int newPersonID = createPerson(person);
        if (entries.size() > 0)
            createEntries(entries, newPersonID);

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
            boolean isValid = setTechAndSolution(entry.getBranch(), person.getRepoUrl(), entry);
            if (isValid) {
                setEntryChallenge(entry);
                entries.add(entry);
            }
        }
    }

    private void updateEntrySolution(EntryDTO entry, String fileSolution) {
        byte[] solutionInBytes = fileSolution.getBytes();
        entry.setSolution(solutionInBytes);
    }

    private void setEntryChallenge(EntryDTO entry) {
        entry.setChallengeId(challenge.getChallengeId());
        entry.setDate(new Date());
    }

    private boolean setTechAndSolution(String branch, String URL, EntryDTO entry) throws JSONException {
        String path = getPath();
        URL = URL + "/contents/" + path + "?ref=" + branch;
        return TraverseFileDirectory(entry, URL);
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

    @Transactional
    private void createEntries(List<EntryDTO> entries, int newPersonID) {
        for (EntryDTO entry : entries) {
            List<EntryDTO> entryExsists = entryService.searchEntrys("url", entry.getUrl());
            if (entryExsists.size() > 0) {
                EntryDTO oldEntry = entryExsists.get(0);
                updateEntry(oldEntry,entry);
            } else {
                addEntry(entry,newPersonID);
            }
        }
    }
    @Transactional
    private void addEntry(EntryDTO entry, int newPersonID) {
        entry.setPersonId(newPersonID);
        EntryDTO createdEntry = entryService.createEntry(entry);
        scoreService.addScore(createdEntry);
    }

    @Transactional
    private void updateEntry(EntryDTO oldEntry1,EntryDTO entry) {
        if (oldEntry1.getResult() != entry.getResult()) {
            EntryDTO oldEntry = entryService.findEntry(oldEntry1.getEntryId());
            oldEntry.setResult(entry.getResult());
            EntryDTO storeEntry = entryService.updateEntry(oldEntry);
            scoreService.addScore(storeEntry);
        }
    }
    @Transactional
    private int createPerson(PersonDTO person) {
        List<PersonDTO> personList = personService.searchPersons("username", person.getUsername());
        if (personList.size() > 0)
            return personList.get(0).getPersonId();

        PersonDTO newPerson = personService.createPerson(person);
        return newPerson.getPersonId();
    }

    private void buildPerson(PersonDTO person, List<EntryDTO> entries, JSONObject fork) throws JSONException {

        person.setScore(0);
        person.setUsername(getUsername(fork));
        person.setUrl("https://github.com/" + person.getUsername());
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

    private String buildRepoURL(JSONObject fork) throws JSONException {
        return "https://api.github.com/repos/" + fork.get("full_name");
    }

    private String getUsername(JSONObject fork) throws JSONException {
        JSONObject owner = fork.getJSONObject("owner");
        String userName = owner.getString("login");
        return userName;
    }

    private boolean TraverseFileDirectory(EntryDTO entry, String URL) throws JSONException {
        JSONArray directoryContent = getJSONFromURL(URL);

        if (directoryContent == null)
            return false;

        for (int contentNumber = 0; contentNumber < directoryContent.length(); contentNumber++) {

            JSONObject content = directoryContent.getJSONObject(contentNumber);
            String name = content.getString("name");
            TechnologyDTO technology = extensionExtractor.extractExtension(name);
            if (technology != null) {
                if (technology.getDescription().equals("Directory")) {
                    String directory = content.getString("url");
                    if (TraverseFileDirectory(entry, directory))
                        return true;

                    if (isCompletedEntry(entry))
                        return true;

                } else {
                    entry.setTechId(technology.getTechId());
                    if (isCompletedEntry(entry))
                        return true;
                }
            } else {
                if (extensionExtractor.isSolutionFile(name, challenge.getSolutionFilePath())) {
                    String downloadUrl = content.getString("download_url");
                    addSolutionToEntry(entry, downloadUrl);

                    if (isCompletedEntry(entry))
                        return true;
                }
            }
        }
        return false;
    }

    private void addSolutionToEntry(EntryDTO entry, String downloadUrl) {
        String submittedSolutionContent = urlService.getContent(downloadUrl);

        updateEntrySolution(entry, submittedSolutionContent);

        boolean isCorrect = urlService.compareSolution(submittedSolutionContent, challenge);
        if (isCorrect) {
            entry.setResult(2);
        } else {
            entry.setResult(1);
        }
    }

    private boolean isCompletedEntry(EntryDTO entry) {
        return entry.getTechId() != 0 && entry.getSolution() != null;
    }

    private JSONArray getJSONFromURL(String URL) {
        String results =  urlService.getContent(URL);
        try {
            JSONArray jsonResults = new JSONArray(results);
            return jsonResults;
        } catch (JSONException e) {
            return null;
        }
    }

    private JSONObject getJSONObjectFromURL(String URL) {
        String results =  urlService.getContent(URL);
        try {
            JSONObject jsonResults = new JSONObject(results);
            return jsonResults;
        } catch (JSONException e) {
            return null;
        }
    }


}

package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;
import com.prolificidea.templates.tsw.services.DTOs.EntryDTO;
import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;
import com.prolificidea.templates.tsw.services.providers.ChallengeService;
import com.prolificidea.templates.tsw.services.providers.EntryService;
import com.prolificidea.templates.tsw.services.providers.PersonService;
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

/**
 * Created by stuart.callen on 2016/03/03.
 */
@Service
public class PersonAndEntryFactoryImpl {


    @Autowired
    PersonService personService;

    @Autowired
    EntryService entryService;

    @Autowired
    ChallengeService challengeService;

    @Autowired
    UrlServiceImpl urlService;

    public ChallengeDTO challenge;

    private RestTemplate restCall = new RestTemplate();

    public String getEntryRepo( int challengeId) throws JSONException {

        challenge= challengeService.findChallenge(challengeId);
        String URLForks = "https://api.github.com/repos"+ challenge.getUrl() +"/forks";
        JSONArray forks = getJSONFromURL(URLForks);
        String userRepoURL = "End";
        for (int forkNumber = 0; forkNumber  < forks.length(); forkNumber ++) {
            JSONObject fork = forks.getJSONObject(forkNumber);
            CreatePeopleAndEntries(fork);
 /*           userRepoURL = buildRepoURL(fork);
            JSONArray userRepoBranches = getJSONFromURL(userRepoURL+"/branches");
            checkBranches(userRepoBranches,fork,userRepoURL+"/branches");*/
        }
        return userRepoURL ;
    }

    private void CreatePeopleAndEntries(JSONObject fork) throws JSONException {
        PersonDTO person = new PersonDTO();
        List<EntryDTO> entries = new ArrayList<EntryDTO>();
        buildPerson(person,entries,fork);
        buildEntries(person,entries,fork);

        if (entries.size() >0) {
            int newPersonID = createPerson(person);
            createEntries(entries ,newPersonID);
        }

    }

    private void buildEntries(PersonDTO person, List<EntryDTO> entries, JSONObject fork) throws JSONException {
        String branchesUrl = person.getUrl()+"/branches";
        JSONArray branches = getJSONFromURL(branchesUrl);
        for (int branchNumber = 0; branchNumber < branches.length(); branchNumber ++)
        {
            JSONObject branch = branches.getJSONObject(branchNumber);
            EntryDTO  entry = new EntryDTO();
            entry.setBranch(branch.getString("name"));
            entry.setFullName(fork.getString("full_name"));
            entry.setUrl(person.getUrl()+"/branches/"+ entry.getBranch());

            EntryDTO validEntry = createAndCheckEntry(entry);

            if (validEntry!=null)
            {
                entries.add(validEntry);
            }
        }
    }

    private EntryDTO createAndCheckEntry(EntryDTO entry) {
        boolean valid= checkSolutionExists(entry);
        if (valid)// need validaty checker
        {
            setEntry(entry);
        }
        else
        {
            entry =null;
        }

        return entry;
    }

    private boolean checkSolutionExists(EntryDTO entry){
        urlService.setOwnerRepoBranchFile(entry.getFullName(),entry.getBranch(),challenge.getSolutionFilePath());
       String fileSolution= urlService.getContent();
        if (fileSolution.equals(""))
            return false;
        // TODO: 2016/03/04 set solution file of entry
        boolean isCorrect =urlService.compareSolution(fileSolution,challenge.getChallengeId());
        if (isCorrect)
        {
            entry.setResult(2);
        }
        else
        {
            entry.setResult(1);
        }
        return true;
    }

    private void setEntry(EntryDTO entry) {
        entry.setChallengeId(challenge.getChallengeId());
        entry.setDate(new Date());
        entry.setSolution(new byte[]{1,123,123,124,34,12});// TODO: 2016/03/03 set this in check solution
        entry.setTechId(1);// TODO: 2016/03/03 set when fixing tech
    }

    private void createEntries(List<EntryDTO> entries, int newPersonID) {
        EntryDTO store;
        for(EntryDTO entry:entries)
        {
            List<EntryDTO> entryExsists = entryService.searchEntrys("url",entry.getUrl());
            if (entryExsists.size()> 0) {
                EntryDTO oldEntry = entryExsists.get(0);
                if(oldEntry.getResult() != entry.getResult())
                {
                    oldEntry.setResult(entry.getResult());
                    store= entryService.updateEntry(oldEntry);
                    //store.getResult();
                }

                return;
            }
            entry.setPersonId(newPersonID);
            entryService.createEntry(entry);
        }

    }

    private int createPerson(PersonDTO person){
        List<PersonDTO> personList =personService.searchPersons("username",person.getUsername());
        if(personList.size()> 0)
        {
            return personList.get(0).getPersonId();
        }
        PersonDTO newPerson=personService.createPerson(person);
        return newPerson.getPersonId();
    }

    private void buildPerson(PersonDTO person, List<EntryDTO> entries, JSONObject fork) throws JSONException {

        person.setScore(0);
        person.setUsername(getUsername(fork));
        person.setUrl(buildRepoURL(fork));
        // TODO: 2016/03/03 fetch user names

    }

    private String buildRepoURL(JSONObject fork) throws JSONException {
        return "https://api.github.com/repos/"+fork.get("full_name");
        // https://api.github.com/repos/{full_name}/
    }

    private String getUsername(JSONObject fork) throws JSONException {
        JSONObject owner = fork.getJSONObject("owner");
        String userName = owner.getString("login");
        return userName;
    }


    private JSONArray getJSONFromURL(String URL) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Basic c3R1YXJ0LmNhbGxlbkBlbnRlbGVjdC5jby56YTo4OTBpb3Bqa2xibm0=");
        HttpEntity<String> entity = new HttpEntity<String>("parameters",headers);

        ResponseEntity<String> results = restCall.exchange(
                URL,
                HttpMethod.GET, entity,String.class);

        try {
            JSONArray jsonResults = new JSONArray(results.getBody());
            return jsonResults;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}

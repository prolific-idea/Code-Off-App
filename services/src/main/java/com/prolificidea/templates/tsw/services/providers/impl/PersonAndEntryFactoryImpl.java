package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.services.DTOs.EntryDTO;
import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;
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

    private RestTemplate restCall = new RestTemplate();

    public String getEntryRepo(String URLForks) throws JSONException {
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

    public void CreatePeopleAndEntries(JSONObject fork) throws JSONException {
        PersonDTO person = new PersonDTO();
        List<EntryDTO> entries = new ArrayList<EntryDTO>();
        buildPerson(person,entries,fork);
        buildEntries(person,entries);

        // TODO: 2016/03/03 create person first before netieis but only if there is entries added things here
        if (entries.size() >0) {
            int newPersonID = createPerson(person);
            createEntries(entries ,newPersonID);
        }

    }

    private void buildEntries(PersonDTO person, List<EntryDTO> entries) throws JSONException {
        String branchesUrl = person.getUrl()+"/branches";
        JSONArray branches = getJSONFromURL(branchesUrl);
        for (int branchNumber = 0; branchNumber < branches.length(); branchNumber ++)
        {
            JSONObject branch = branches.getJSONObject(branchNumber);
            EntryDTO  entry = new EntryDTO();
            entry.setUrl(person.getUrl()+"/branches/"+ branch.getString("name"));

            EntryDTO validEntry = createAndCheckEntry(entry);

            if (validEntry!=null)
            {
                entries.add(validEntry);
            }
        }
    }

    private EntryDTO createAndCheckEntry(EntryDTO entry) {
            // TODO: 2016/03/03 check if it has solution value  one function
            // TODO: 2016/03/03  mark solution and assign score one function
        // TODO: 2016/03/03 add entry to valid list
        if (true)// need validaty checker
        {
            setEntry(entry);
        }

        return entry; // TODO: 2016/03/03   not correct fix this
    }

    private void setEntry(EntryDTO entry) {
        entry.setChallengeId(1); // TODO: 2016/03/03  set values to the current challnge
        entry.setDate(new Date());
        entry.setSolution(new byte[]{1,123,123,124,34,12});// TODO: 2016/03/03 set this in check solution
        entry.setResult(1);// TODO: 2016/03/03 set this when checking answer
        entry.setTechId(1);// TODO: 2016/03/03 set when fixing tech
    }

    private void createEntries(List<EntryDTO> entries, int newPersonID) {
        for(EntryDTO entry:entries)
        {
            List<EntryDTO> entryExsists = entryService.searchEntrys("url",entry.getUrl());
            if (entryExsists.size()> 0) {
                return ;
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


    public JSONArray getJSONFromURL(String URL) {
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

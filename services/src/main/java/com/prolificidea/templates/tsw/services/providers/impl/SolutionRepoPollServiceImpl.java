package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.domain.entities.Person;
import com.prolificidea.templates.tsw.services.DTOs.EntryDTO;
import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;
import com.prolificidea.templates.tsw.services.providers.ChallengeService;
import com.prolificidea.templates.tsw.services.providers.EntryService;
import com.prolificidea.templates.tsw.services.providers.PersonService;
import com.prolificidea.templates.tsw.services.providers.TechnologyService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by stuart.callen on 2016/03/02.
 */
@Service
@EnableScheduling
public class SolutionRepoPollServiceImpl {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static int challengeID =1;

    @Autowired
    ChallengeService challengeService;
    @Autowired
    PersonService personService;
    @Autowired
    TechnologyService technologyService;
    @Autowired
    EntryService entryService;

    private RestTemplate restCall = new RestTemplate();

    private String time;

    @Scheduled(fixedRate = 60000 * 30)//Should be 60000*30
    public void reportCurrentTime() {
        time = "The time is now " + dateFormat.format(new Date());
        // TODO: 2016/03/02  implmnet polling of entries already created;
        // TODO: 2016/03/02  look to see if something is forked and create a new entry;
    }

    private void addEntry(JSONObject fork) throws JSONException {

        EntryDTO entry =new EntryDTO();
        entry.setUrl(buildRepoURL(fork));
        List<EntryDTO> entryExsists = entryService.searchEntrys("url",entry.getUrl());
        if (entryExsists.size()> 0) {
            return ;
        }
        entry.setChallengeId(challengeID);
        entry.setDate(new Date());

        int person = createPerson(fork);
        entry.setPersonId(person);
        entry.setUrl(buildRepoURL(fork));
        entry.setSolution(new byte[]{1,123,123,124,34,12});
        entry.setTechId(1);
        entryService.createEntry(entry);
    }

    private int createPerson(JSONObject fork) throws JSONException {

        PersonDTO person = new PersonDTO();
        person.setUsername(getUsername(fork));
        List<PersonDTO> personList =personService.searchPersons("username",person.getUsername());
        if(personList.size() >0)
        {
            return personList.get(0).getPersonId();
        }
        person.setUrl(buildRepoURL(fork));
        person.setScore(0);
        //// TODO: 2016/03/03 fetch user names
        PersonDTO newPerson=personService.createPerson(person);
        return newPerson.getPersonId();

    }

    private String getUsername(JSONObject fork) throws JSONException {
        JSONObject owner = fork.getJSONObject("owner");
        String userName = owner.getString("login");
        return userName;
    }

    public String getEntryRepo(JSONArray forks) throws JSONException {
        String userRepoURL = "Failed";
        for (int forkNumber = 0; forkNumber  < forks.length(); forkNumber ++) {
            JSONObject fork = forks.getJSONObject(forkNumber);
            userRepoURL = buildRepoURL(fork);
            JSONArray userRepoBranches = getJSONFromURL(userRepoURL+"/branches");
            checkBranches(userRepoBranches);
            addEntry( fork);
        }
        return userRepoURL ;
    }

    private void checkBranches(JSONArray repoBranches) {
       // String userRepoURL = buildRepoURL(repoBranches);


       // https://api.github.com/repos/{}/Code-Off/branches
    }

    private String buildRepoURL(JSONObject fork) throws JSONException {
        return "https://api.github.com/repos/"+fork.get("full_name");
        // https://api.github.com/repos/{full_name}/
    }


    private void addEntries(JSONArray forks){

    }
    public JSONArray getJSONFromURL(String URL) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Basic c3R1YXJ0LmNhbGxlbkBlbnRlbGVjdC5jby56YTo4OTBpb3Bqa2xibm0=");
        HttpEntity<String> entity = new HttpEntity<String>("parameters",headers);

        ResponseEntity<String> results = restCall.exchange(
                "https://api.github.com/repos/prolific-idea/Code-Off/forks",
                HttpMethod.GET, entity,String.class);

        try {
            JSONArray jsonResults = new JSONArray(results.getBody());
            return jsonResults;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTime() {
        return time;
    }

}

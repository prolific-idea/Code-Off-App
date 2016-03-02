package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.services.providers.EntryService;
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

/**
 * Created by stuart.callen on 2016/03/02.
 */
@Service
@EnableScheduling
public class SolutionRepoPollServiceImpl {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    private RestTemplate restCall = new RestTemplate();

    @Autowired
    EntryService entryService;


    private String time;

    @Scheduled(fixedRate = 60000 * 30)//Should be 60000*30
    public void reportCurrentTime() {
        time = "The time is now " + dateFormat.format(new Date());
        // TODO: 2016/03/02  implmnet polling of entries already created;
        // TODO: 2016/03/02  look to see if something is forked and create a new entry;
    }

    private void addEntry(JSONArray forks) {

    /*    Entry entry = new Entry();
        entry.setChallengeId(1);
        entry.setDate(new Date());
        entry.setPersonId(1);
        entry.setUrl("/url");
        entry.setSolution();
        entry.setTechId();*/
    }

    public String getEntryRepo(JSONArray forks) throws JSONException {
        for (int forkNumber = 0; forkNumber  < forks.length(); forkNumber ++) {
            JSONObject fork = forks.getJSONObject(forkNumber);
            String userRepoURL = buildRepoURL(fork);
            JSONArray userRepoBranches = getJSONFromURL(userRepoURL+"/branches");
            checkBranches(userRepoBranches);
            return userRepoURL;

        }
        return "failed";
    }

    private void checkBranches(JSONArray fork) {


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

package com.prolificidea.templates.tsw.services.providers.impl;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PersonAndEntryFactoryImpl personAndEnt;

    private RestTemplate restCall = new RestTemplate();

    private String time;

    private final int ONE_MINUTE =60000;

    private int challengeID =0; // needs to be removed when scheduling between dates

    @Scheduled(fixedRate = ONE_MINUTE  * 5)//Should be 60000*30
    public void pollRepositoryForSolution() {
        time = "The time is now " + dateFormat.format(new Date());
        try {
            if (challengeID != 0)
            personAndEnt.markSolutionsOfUserIfTheyExsistForAChallenge(challengeID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getChallengeID() {
        return challengeID;
    }

    public void setChallengeID(int challengeID) {
        this.challengeID = challengeID;
    }

    public String getTime() {
        return time;
    }

}

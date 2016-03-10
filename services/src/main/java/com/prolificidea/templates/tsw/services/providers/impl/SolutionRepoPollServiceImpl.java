package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.services.providers.PersonAndEntryFactory;
import com.prolificidea.templates.tsw.services.providers.SolutionRepoPollService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by stuart.callen on 2016/03/02.
 */
@Service
@EnableScheduling
public class SolutionRepoPollServiceImpl  implements SolutionRepoPollService{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private PersonAndEntryFactory personAndEnt;

    @Autowired
    private RestOperations restCall ;

    private String time;

    private final int ONE_MINUTE =60000;

    private int challengeID =0; // needs to be removed when scheduling between dates

    @Scheduled(fixedRate = ONE_MINUTE  * 5)//Should be 60000*30
    public void pollRepositoryForSolution() {
        time = "Last Polled: " + dateFormat.format(new Date()) ;
        String temp = time;
        try {
            time = temp +" CurrentlyPolling!";
            if (challengeID != 0)
            personAndEnt.markSolutionsOfUserIfTheyExsistForAChallenge(challengeID);
            time = temp +" Finished Polling At: " + dateFormat.format(new Date());
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

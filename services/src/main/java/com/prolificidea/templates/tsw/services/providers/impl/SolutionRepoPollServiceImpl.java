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
    private static int challengeID =1;

    @Autowired
    private PersonAndEntryFactoryImpl personAndEnt;

    private RestTemplate restCall = new RestTemplate();

    private String time;

    @Scheduled(fixedRate = 60000 * 5)//Should be 60000*30
    public void pollRepositoryForSolution() {
        time = "The time is now " + dateFormat.format(new Date());
        try {
            personAndEnt.markSolutionsOfUserIfTheyExsistForAChallenge(101);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTime() {
        return time;
    }

}

package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;
import com.prolificidea.templates.tsw.services.providers.ChallengeService;
import com.prolificidea.templates.tsw.services.providers.SolutionRepoPollService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by stuart.callen on 2016/03/02.
 */
@Service
@EnableScheduling
public class SolutionRepoPollServiceImpl  implements SolutionRepoPollService{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private PersonAndEntryFactoryImpl personAndEnt;

    @Autowired
    private RestOperations restCall ;

    @Autowired
    ChallengeService challengeService;


    private String time;

    private final int ONE_MINUTE =60000;

    private final int MINUTES_BETWEEN_POLLS = 10;

    private int challengeID =0; // needs to be removed when scheduling between dates

    @Scheduled(fixedRate = ONE_MINUTE  * MINUTES_BETWEEN_POLLS)//Should be 60000*30
    public void pollRepositoryForSolution() {

        challengeID += 1;
        time = Integer.toString(challengeID)+"Last Polled: " + dateFormat.format(new Date()) ;
        String temp = time;
        try {
            time = temp +" CurrentlyPolling!";

            List<ChallengeDTO> challenges = currentlyRunningChallenges();
            if (challenges.size() >0){
                pollMultipleChallenges(challenges);
            }
            lastRunner ();
            time = temp +" Finished Polling At: " + dateFormat.format(new Date());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private List<ChallengeDTO> currentlyRunningChallenges(){
        return challengeService.getChallengesThatAreOnGoing();
    }
    private void pollMultipleChallenges(List<ChallengeDTO> challenges) throws JSONException {
        for (ChallengeDTO challenge :challenges){
            personAndEnt.markSolutionsOfUserIfTheyExsistForAChallenge(challenge);
        }
    }

    private List<ChallengeDTO> challengesEndingInLessThenPollingTime(){
        List<ChallengeDTO> challengesLessThenPollingTime = new ArrayList<ChallengeDTO>();
        List<ChallengeDTO> challenges = challengeService.findAllChallenges();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextPoll = now.plusMinutes(MINUTES_BETWEEN_POLLS);


        Date nowDate = convertLocalDateTimeToDate(now);
        Date pollDate = convertLocalDateTimeToDate(nextPoll);
        for (ChallengeDTO challenge: challenges)
        {
            if ((challenge.getEndDate().after(nowDate))&& (challenge.getEndDate().before(pollDate)))
            {
               challengesLessThenPollingTime.add(challenge);
            }
        }
        return challengesLessThenPollingTime;
    }


    private Date convertLocalDateTimeToDate(LocalDateTime dateTime)
    {Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);}

    public int getChallengeID() {
        return challengeID;
    }

    public void setChallengeID(int challengeID) {
        this.challengeID = challengeID;
    }

    public String getTime() {
        return time;
    }

    private void lastRunner ()
    {List<ChallengeDTO> challenges =challengesEndingInLessThenPollingTime();
        Timer timer = new Timer();
        for(ChallengeDTO challenge : challenges)
        {
            timer.schedule( new Task(challenge), challenge.getEndDate());
        }
    }

    private class Task extends TimerTask {

        private ChallengeDTO chal;

        public Task(ChallengeDTO chal)
        {super();
        this.chal = chal;}

        @Override
        public void run() {
            try {
                personAndEnt.markSolutionsOfUserIfTheyExsistForAChallenge(chal);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



}

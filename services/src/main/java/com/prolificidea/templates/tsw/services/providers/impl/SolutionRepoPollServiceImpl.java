package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;
import com.prolificidea.templates.tsw.services.providers.ChallengeService;
import com.prolificidea.templates.tsw.services.providers.SolutionRepoPollService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@EnableScheduling
public class SolutionRepoPollServiceImpl  implements SolutionRepoPollService{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final int ONE_MINUTE =60000;
    private final int MINUTES_BETWEEN_POLLS = 8;

    private PollingReport pollReport = new PollingReport();

    @Autowired
    private PersonAndEntryFactoryImpl personAndEnt;

    @Autowired
    private RestOperations restCall ;

    @Autowired
    ChallengeService challengeService;

    public JSONObject getReport() {
        return pollReport.getReport();
    }

    @Scheduled(fixedRate = ONE_MINUTE  * MINUTES_BETWEEN_POLLS)//Should be 60000*30
    public void pollRepositoryForSolution() throws JSONException {
        pollReport.intalize(MINUTES_BETWEEN_POLLS);
        try {
            List<ChallengeDTO> challenges = currentlyRunningChallenges();
            if (challenges.size() >0){
               pollMultipleChallenges(challenges);
                lastRunner (challenges);
            }
            pollReport.endReport();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private List<ChallengeDTO> currentlyRunningChallenges(){
        return challengeService.getChallengesThatAreOnGoing();
    }

    private void pollMultipleChallenges(List<ChallengeDTO> challenges) throws JSONException {
        JSONArray challengesArray =new JSONArray();
        int challengeNumber = 0;
        for (ChallengeDTO challenge :challenges){
            try {
                personAndEnt.markSolutionsOfUserIfTheyExsistForAChallenge(challenge);
                challengesArray.put(challengeNumber,getJSONObjectForChallenge(challenge,0));
            }catch(Exception e)
            {
                challengesArray.put(challengeNumber,getJSONObjectForChallenge(challenge,1));
            }
            challengeNumber ++;
        }
        pollReport.putArray("challenges_polled",challengesArray);
    }

    private JSONObject getJSONObjectForChallenge(ChallengeDTO challenge, int status) {
        JSONObject challengeObject = new JSONObject();
        try {
            challengeObject.put("challenge_number", challenge.getCodeOffNumber());
            challengeObject.put("status",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return challengeObject;
    }

    private List<ChallengeDTO> challengesEndingInLessThenPollingTime(List<ChallengeDTO> challenges ){
        List<ChallengeDTO> challengesLessThenPollingTime = new ArrayList<ChallengeDTO>();
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

    private void lastRunner (List<ChallengeDTO> challengesToBePolled )
    {List<ChallengeDTO> challenges =challengesEndingInLessThenPollingTime(challengesToBePolled );
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

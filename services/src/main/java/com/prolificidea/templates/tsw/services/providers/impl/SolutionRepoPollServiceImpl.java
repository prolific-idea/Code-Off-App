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
    private final int MINUTES_BETWEEN_POLLS = 4;

    private JSONObject report = new JSONObject();

    private String time;

    @Autowired
    private PersonAndEntryFactoryImpl personAndEnt;

    @Autowired
    private RestOperations restCall ;

    @Autowired
    ChallengeService challengeService;

    public JSONObject getReport() {
        return report;
    }

    public String getTime() {
        return time;
    }

    @Scheduled(fixedRate = ONE_MINUTE  * MINUTES_BETWEEN_POLLS)//Should be 60000*30
    public void pollRepositoryForSolution() throws JSONException {
        intailizeReport();
        time = "Last Polled: " + dateFormat.format(new Date()) ;
        String temp = time;
        try {
            time = temp +" CurrentlyPolling!";

            List<ChallengeDTO> challenges = currentlyRunningChallenges();
            if (challenges.size() >0){
               pollMultipleChallenges(challenges);
            }
            lastRunner ();;
            time = temp +" Finished Polling At: " + dateFormat.format(new Date());
            endReport();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        time = "Excpetion";
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
        report.put("challenges_polled",challengesArray);
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

    private void intailizeReport(){
        try {
            report.put("last_polled", dateFormat.format(new Date()).toString());
            report.put("polling_status","Currently Polling");
            LocalDateTime time = LocalDateTime.now();
            time = time.plusMinutes(MINUTES_BETWEEN_POLLS);
            report.put("next_poll",time.toLocalTime().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void endReport(){
        try {
            report.put("polling_status","Finished Polling");
            report.put("finished_polled", dateFormat.format(new Date()).toString());
        } catch (JSONException e) {
            e.printStackTrace();
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

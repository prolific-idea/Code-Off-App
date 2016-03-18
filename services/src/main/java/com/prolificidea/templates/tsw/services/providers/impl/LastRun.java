package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LastRun implements Runnable{

    @Autowired
    private PersonAndEntryFactoryImpl personAndEnt;

    public ChallengeDTO getChallenge() {
        return challenge;
    }

    public void setChallenge(ChallengeDTO challenge) {
        this.challenge = challenge;
    }

    private ChallengeDTO challenge ;

    public void run() {
        if (challenge != null)
        {
            try {
                personAndEnt.markSolutionsOfUserIfTheyExsistForAChallenge(challenge);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}

package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.services.providers.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeRestController {
    @Autowired
    ChallengeService challengeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Challenge> getAllChallenges() {
        List<Challenge> challenges = challengeService.findAllChallenges();
        return challenges;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Challenge getChallengeByID(@PathVariable int id) {
        Challenge challenge = challengeService.findChallenge(id);
        return challenge;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public
    @ResponseBody
    Challenge createChallenge(@RequestBody Challenge challengeToBeCreated) {
        Challenge challenge = challengeService.createChallenge(challengeToBeCreated);
        return challenge;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public
    @ResponseBody
    Challenge updateChallenge(@RequestBody Challenge challengeToBeUpdated) {
        Challenge challenge = challengeService.updateChallenge(challengeToBeUpdated);
        return challenge;
    }

}

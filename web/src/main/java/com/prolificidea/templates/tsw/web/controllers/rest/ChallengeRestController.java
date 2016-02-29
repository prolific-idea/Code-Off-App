package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.domain.AppUserDetails;
import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.services.providers.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChallengeRestController {
    @Autowired
    ChallengeService challengeService;

    @RequestMapping(value = "/api/Challenges", method = RequestMethod.GET)
    public List<Challenge> getAllChallenges() {
       return  challengeService.findAllChallenges();
    }
    @RequestMapping(value = "/api/Challenges/{object}", method = RequestMethod.POST)
    public @ResponseBody Challenge createChallenge(@PathVariable Challenge object) {
        return  challengeService.createChallenge(object);
    }

    @RequestMapping(value = "/api/Challenges/{object}", method = RequestMethod.PUT)
    public @ResponseBody Challenge updateChallenge(@PathVariable Challenge object) {
        return  challengeService.createChallenge(object);
    }

}

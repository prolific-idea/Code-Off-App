package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.services.providers.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    List<Challenge> getAllChallenges(@RequestParam(required = false, defaultValue = "0") int pageSize  , @RequestParam(required = false, defaultValue = "0") int pageNum) {
        List<Challenge> challenges;
        if (pageNum == 0 && pageSize == 0)
        {
            challenges = challengeService.findAllChallenges();
        }
        else {
            challenges = challengeService.findAllChallenges(pageSize, pageNum);
        }
        return challenges;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Challenge> getChallengeByID(@PathVariable int id) {
        if (id <= 0)
            return new ResponseEntity<Challenge>(HttpStatus.BAD_REQUEST);

        Challenge challenge = challengeService.findChallenge(id);

        if (challenge == null) {
            return new ResponseEntity<Challenge>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Challenge>(challenge, HttpStatus.FOUND);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createChallenge(@RequestBody Challenge challengeToBeCreated) {
        if (challengeToBeCreated.getSolution() == null || challengeToBeCreated.getSolution().length == 0)
            return new ResponseEntity<String>(new String("Solution file is required."), HttpStatus.NOT_ACCEPTABLE);
        if (challengeToBeCreated.getStartDate() == null)
            return new ResponseEntity<String>(new String("Start date cannot be null."), HttpStatus.NOT_ACCEPTABLE);
        if (challengeToBeCreated.getStartDate().compareTo(challengeToBeCreated.getEndDate()) > 0)
            return new ResponseEntity<String>(new String("Start date has to before end date."), HttpStatus.NOT_ACCEPTABLE);

        Challenge challenge = challengeService.createChallenge(challengeToBeCreated);
        return new ResponseEntity<Challenge>(challenge, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateChallenge(@RequestBody Challenge challengeToBeUpdated) {
        if (challengeToBeUpdated.getChallengeId() <= 0)
            return new ResponseEntity<String>(new String("Please select a valid challenge."), HttpStatus.NOT_ACCEPTABLE);
        if (challengeToBeUpdated.getSolution() == null || challengeToBeUpdated.getSolution().length == 0)
            return new ResponseEntity<String>(new String("Solution file is required."), HttpStatus.NOT_ACCEPTABLE);
        if (challengeToBeUpdated.getStartDate() == null)
            return new ResponseEntity<String>(new String("Start date cannot be null."), HttpStatus.NOT_ACCEPTABLE);
        if (challengeToBeUpdated.getStartDate().compareTo(challengeToBeUpdated.getEndDate()) > 0)
            return new ResponseEntity<String>(new String("Start date has to before end date."), HttpStatus.NOT_ACCEPTABLE);
        Challenge challenge = challengeService.updateChallenge(challengeToBeUpdated);

        return new ResponseEntity<Challenge>(challenge, HttpStatus.OK);
    }

}

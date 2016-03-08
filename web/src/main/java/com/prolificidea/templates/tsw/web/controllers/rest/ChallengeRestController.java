package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.services.DTOs.ChallengeCountDTO;
import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;
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
    List<ChallengeDTO> getAllChallenges(@RequestParam(required = false, defaultValue = "0") int pageSize  , @RequestParam(required = false, defaultValue = "0") int pageNum) {
        List<ChallengeDTO> challenges;
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
    public ResponseEntity<ChallengeDTO> getChallengeByID(@PathVariable int id) {
        if (id <= 0)
            return new ResponseEntity<ChallengeDTO>(HttpStatus.BAD_REQUEST);

        ChallengeDTO challenge = challengeService.findChallenge(id);

        if (challenge == null) {
            return new ResponseEntity<ChallengeDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ChallengeDTO>(challenge, HttpStatus.FOUND);
    }


    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public Object getChallengeCount() {
        ChallengeCountDTO count = new ChallengeCountDTO();
        long numberOfChallenges = challengeService.countChallenges();
        count.setCountOfChallenges(numberOfChallenges);
        return count;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createChallenge(@RequestBody ChallengeDTO challengeToBeCreated) {
        if (challengeToBeCreated.getSolution() == null || challengeToBeCreated.getSolution().length == 0)
            return new ResponseEntity<String>(new String("Solution file is required."), HttpStatus.NOT_ACCEPTABLE);
        if (challengeToBeCreated.getStartDate() == null)
            return new ResponseEntity<String>(new String("Start date cannot be null."), HttpStatus.NOT_ACCEPTABLE);
        if (challengeToBeCreated.getStartDate().compareTo(challengeToBeCreated.getEndDate()) > 0)
            return new ResponseEntity<String>(new String("Start date has to before end date."), HttpStatus.NOT_ACCEPTABLE);

        ChallengeDTO challenge = challengeService.createChallenge(challengeToBeCreated);
        return new ResponseEntity<ChallengeDTO>(challenge, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateChallenge(@RequestBody ChallengeDTO challengeToBeUpdated) {
        if (challengeToBeUpdated.getChallengeId() <= 0)
            return new ResponseEntity<String>(new String("Please select a valid challenge."), HttpStatus.NOT_ACCEPTABLE);
        if (challengeToBeUpdated.getSolution() == null || challengeToBeUpdated.getSolution().length == 0)
            return new ResponseEntity<String>(new String("Solution file is required."), HttpStatus.NOT_ACCEPTABLE);
        if (challengeToBeUpdated.getStartDate() == null)
            return new ResponseEntity<String>(new String("Start date cannot be null."), HttpStatus.NOT_ACCEPTABLE);
        if (challengeToBeUpdated.getStartDate().compareTo(challengeToBeUpdated.getEndDate()) > 0)
            return new ResponseEntity<String>(new String("Start date has to before end date."), HttpStatus.NOT_ACCEPTABLE);
        ChallengeDTO challenge = challengeService.updateChallenge(challengeToBeUpdated);

        return new ResponseEntity<ChallengeDTO>(challenge, HttpStatus.OK);
    }

}

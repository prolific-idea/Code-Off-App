package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.domain.entities.Person;
import com.prolificidea.templates.tsw.services.providers.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardRestController {

    @Autowired
    PersonService personService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Person> getLeaderboard() {
        List<Person> challenges = personService.findAllPersons();
        return challenges;
    }
}

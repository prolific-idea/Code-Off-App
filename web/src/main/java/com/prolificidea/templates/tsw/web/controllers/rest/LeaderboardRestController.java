package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.services.DTOs.LeaderboardDTO;
import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;
import com.prolificidea.templates.tsw.services.providers.LeaderboardService;
import com.prolificidea.templates.tsw.services.providers.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardRestController {

    @Autowired
    PersonService personService;

    @Autowired
    LeaderboardService leaderboardService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public
    @ResponseBody
    List<PersonDTO> getLeaderboard() {
        List<PersonDTO> challenges = personService.findAllPersons();
        return challenges;
    }


    @RequestMapping(value = "/le", method = RequestMethod.GET)
    public
    @ResponseBody
    List<LeaderboardDTO> getLeaderboards(@RequestParam(required = false, defaultValue = "0") int pageSize,
                                         @RequestParam(required = false, defaultValue = "0") int pageNum)
    {
        if (pageNum == 0 && pageSize == 0) {
            return personService.getLeaderboard();
        } else {
            return personService.getLeaderboard(pageSize, pageNum);
        }

    }

}
 

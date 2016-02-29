package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.domain.entities.Technology;
import com.prolificidea.templates.tsw.services.providers.ChallengeService;
import com.prolificidea.templates.tsw.services.providers.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by stuart.callen on 2016/02/29.
 */
@RestController
public class TechnologyController {
    @Autowired
    TechnologyService technologyService;

    @RequestMapping(value = "/api/technology", method = RequestMethod.GET)
    public List<Technology> getAllTechnology() {
        List<Technology> temp = technologyService.findAllTechnologys();
        return  temp;
    }
}

package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.services.DTOs.TechnologyDTO;
import com.prolificidea.templates.tsw.services.providers.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technology")
public class TechnologyController {
    @Autowired
    TechnologyService technologyService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<TechnologyDTO> getAllTechnology() {
        List<TechnologyDTO> techs = technologyService.findAllTechnologys();
        return  techs;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public  TechnologyDTO getAllTechnology(@PathVariable int id ) {
        TechnologyDTO tech = technologyService.findTechnology(id);
        return  tech;
    }
}

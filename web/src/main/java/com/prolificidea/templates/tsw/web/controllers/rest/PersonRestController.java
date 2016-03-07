package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;
import com.prolificidea.templates.tsw.services.providers.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by matthew.jordaan on 2016/03/04.
 */

@RestController
@RequestMapping("/api/person")
public class PersonRestController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    List<PersonDTO> getAllPersons(@RequestParam(required = false, defaultValue = "0") int pageSize,
                                  @RequestParam(required = false, defaultValue = "0") int pageNum)
    {
        if (pageNum == 0 && pageSize == 0) {
            return personService.findAllPersons();
        }
        else {
            return personService.findAllPersons(pageSize, pageNum);
        }

    }

    @RequestMapping(value = "/desc", method = RequestMethod.GET)
    public @ResponseBody
    List<PersonDTO> getAllPersonsDesc(@RequestParam(required = false, defaultValue = "0") int pageSize,
                                  @RequestParam(required = false, defaultValue = "0") int pageNum)
    {
        if (pageNum == 0 && pageSize == 0) {
            return personService.findAllPersonsDesc();
        }
        else {
            return personService.findAllPersonsDesc(pageSize, pageNum);
        }

    }
}

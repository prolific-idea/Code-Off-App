package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.services.DTOs.PersonCountDTO;
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


    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public @ResponseBody PersonCountDTO getNumberOfPeople(){
        PersonCountDTO count = new PersonCountDTO();
        count.setCountOfPerson(personService.countPersons());
        return count;
    }

    @RequestMapping(value = "/score/{ident}", method = RequestMethod.PUT)
    public @ResponseBody PersonDTO updateScore(@PathVariable int ident,@RequestParam (required = true) int score){
        PersonDTO person = personService.findPerson(ident);
        person.setScore(score);
        return personService.updatePerson(person);
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

package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.domain.entities.Person;
import com.prolificidea.templates.tsw.services.providers.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonRestController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<Person> getAllPersons() {
        List<Person> persons = personService.findAllPersons();
        return persons;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Person getPersonByID(@PathVariable int id) {
        Person person = personService.findPerson(id);
        return person;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> createPerson(@RequestBody Person personToBeCreated) {
        Person person = personService.createPerson(personToBeCreated);
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<?> updatePerson(@RequestBody Person personToBeUpdated) {
        Person person = personService.updatePerson(personToBeUpdated);
        return new  ResponseEntity<Person> (person,HttpStatus.OK);
    }
}

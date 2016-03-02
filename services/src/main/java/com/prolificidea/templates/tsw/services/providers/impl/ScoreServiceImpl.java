package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.services.providers.EntryService;
import com.prolificidea.templates.tsw.services.providers.PersonService;
import com.prolificidea.templates.tsw.services.providers.ScoreService;
import com.prolificidea.templates.tsw.services.providers.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by matthew.jordaan on 2016/03/01.
 */
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    PersonService personService;

    @Autowired
    EntryService entryService;

    @Autowired
    TechnologyService technologyService;


    //
    //Max 2 points per challenge
    //
    //
    //
    //
    //
    //
    //
    //

//
    public void addScore(Entry entry) {
//        Person person = personService.findPerson(entry.getPersonId());
//
//
    }
}

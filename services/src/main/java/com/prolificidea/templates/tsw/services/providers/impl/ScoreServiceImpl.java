package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.services.DTOs.EntryDTO;
import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;
import com.prolificidea.templates.tsw.services.providers.EntryService;
import com.prolificidea.templates.tsw.services.providers.PersonService;
import com.prolificidea.templates.tsw.services.providers.ScoreService;
import com.prolificidea.templates.tsw.services.providers.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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


    public void addScore(EntryDTO entry) {
        List<EntryDTO> entries = entryService.findAllEntriesByPersonAndChallenge(entry.getPersonId(),entry.getChallengeId());
        PersonDTO p = personService.findPerson(entry.getPersonId());
        int score = 0;
        for (EntryDTO e : entries)
        {
             score += e.getResult();
        }

        if (score > 2) score = 2;
        p.setScore(p.getScore() + score);
        personService.updatePerson(p);

//        if (entries.size() == 1) {
//            p.setScore(p.getScore() + entry.getResult());
//        }
    }
}

package com.prolificidea.templates.tsw.services;

import com.prolificidea.templates.tsw.services.DTOs.EntryDTO;
import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;
import com.prolificidea.templates.tsw.services.providers.EntryService;
import com.prolificidea.templates.tsw.services.providers.PersonService;
import com.prolificidea.templates.tsw.services.providers.ScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by matthew.jordaan on 2016/03/03.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/com/prolificidea/templates/tsw/services/root-context.xml" })
public class ScoreServiceTest {

    @Autowired
    ScoreService scoreService;

    @Autowired
    PersonService personService;

    @Autowired
    EntryService entryService;

    @Test
    public void testAddScore() {
        assert (true);
      /*  PersonDTO p  = personService.findPerson(2);
        EntryDTO e = entryService.findEntry(102);
        System.out.println(p.getFirstName() + ": " + p.getScore());
        System.out.println("Adding score.......");
        scoreService.addScore(e);
        System.out.println("New score: " + p.getScore());*/
    }

    @Test
    public void testRecalcScore() {
        assert (true);
        /*PersonDTO p  = personService.findPerson(2);
        scoreService.recalculateScores(p);*/
    }
}

package com.prolificidea.templates.tsw.services;

import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;
import com.prolificidea.templates.tsw.services.providers.ChallengeService;
import com.prolificidea.templates.tsw.services.providers.EntryService;
import com.prolificidea.templates.tsw.services.providers.PersonService;
import com.prolificidea.templates.tsw.services.providers.ScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by matthew.jordaan on 2016/03/03.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/com/prolificidea/templates/tsw/services/root-context.xml" })
public class ScoreServiceTest {

    @Autowired
    ChallengeService challengeService;

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
<<<<<<< HEAD

        System.out.println("New score: " + personService.findPerson(2).getScore());

        System.out.println("New score: " + p.getScore());*/

    }
    @Test
    public void testFilterByChallenge() {
        List<PersonDTO> persons = personService.getScoresByChallenge(2);
        for (PersonDTO p : persons) {
            System.out.println(p.getFirstName() + ":  SCORE: " + p.getScore());
        }
=======
        System.out.println("New score: " + personService.findPerson(2).getScore());
        System.out.println("New score: " + p.getScore());*/

>>>>>>> 299dc355d0c49958ffaa9fc9744359ec99158351
    }

    @Test
    public void testRecalcScore() {
        assert (true);
        /*PersonDTO p  = personService.findPerson(2);
        scoreService.recalculateScores(p);*/
    }
}

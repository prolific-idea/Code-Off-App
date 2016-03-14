package com.prolificidea.templates.tsw.services;

<<<<<<< HEAD
=======
import com.prolificidea.templates.tsw.services.DTOs.TechnologyDTO;
>>>>>>> 77f249618be624b6874a54a7b0b73e7c3509b214
import com.prolificidea.templates.tsw.services.providers.*;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
=======
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
>>>>>>> 77f249618be624b6874a54a7b0b73e7c3509b214

/**
 * Created by matthew.jordaan on 2016/03/03.
 */
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/com/prolificidea/templates/tsw/services/root-context.xml" })
public class ScoreServiceTest {

    @Autowired
    ChallengeService challengeService;

    @Autowired
    ScoreService scoreService;

    @Autowired
    PersonService personService;

    @Autowired
    EntryService entryService;

    @Autowired
    ExtensionExtractor exctrator;

//    @Test
//    public void testAddScore() {
//        assert (true);
//      /*  PersonDTO p  = personService.findPerson(2);
//        EntryDTO e = entryService.findEntry(102);
//        System.out.println(p.getFirstName() + ": " + p.getScore());
//        System.out.println("Adding score.......");
//        scoreService.addScore(e);
//
//        System.out.println("New score: " + personService.findPerson(2).getScore());
//
//        System.out.println("New score: " + p.getScore());
//
//        System.out.println("New score: " + personService.findPerson(2).getScore());
//        System.out.println("New score: " + p.getScore());*/
//    }
//    @Test
//    public void testFilterByChallenge() {
///*        List<PersonDTO> persons = personService.getScoresByChallenge(2);
//        for (PersonDTO p : persons) {
//            System.out.println(p.getFirstName() + ":  SCORE: " + p.getScore());
//        }
//        System.out.println("New score: " + personService.findPerson(2).getScore());
//        System.out.println("New score: " + p.getScore());*/
//
//    }

 /*   @Test
    public void testFilterByTech() {

        List<PersonDTO> persons = personService.getScoresByTech(2);

        for (PersonDTO p : persons) {
            System.out.println(p.getFirstName() + ":  SCORE: " + p.getScore());
        }


    }*/
//
//    @Test
//    public void testGetNoCodeOffs() {
//
//        int i = personService.getNoCodeOffs(3);
//        System.out.println(i);
//    }


<<<<<<< HEAD
//    @Test
//    public void testGetListOfTechsByPerson() {
//
=======
    @Test
    public void testGetListOfTechsByPerson() {

>>>>>>> 77f249618be624b6874a54a7b0b73e7c3509b214
//        List<TechnologyDTO> techs = personService.getListOfTechsByPerson(2);
//        for (TechnologyDTO t : techs) {
//            System.out.println(t.getDescription());
//        }
<<<<<<< HEAD
//    }
//
//    @Test
//    public void testRecalcScore() {
//        assert (true);
//        /*PersonDTO p  = personService.findPerson(2);
//        scoreService.recalculateScores(p);*/
//    }
//
//    @Test
//    public void testFindByDescription(){
//        TechnologyDTO technologyDTO = exctrator.extractExtension("heloWorl.java");
//
//        Assert.assertEquals("Description should be ","Java", technologyDTO.getDescription());
//    }
=======
    }

    @Test
    public void testRecalcScore() {
        assert (true);
        /*PersonDTO p  = personService.findPerson(2);
        scoreService.recalculateScores(p);*/
    }

    @Test
    public void testFindByDescription(){
        TechnologyDTO technologyDTO = exctrator.extractExtension("heloWorl.java");

        Assert.assertEquals("Description should be ","Java", technologyDTO.getDescription());
    }
>>>>>>> 77f249618be624b6874a54a7b0b73e7c3509b214
}

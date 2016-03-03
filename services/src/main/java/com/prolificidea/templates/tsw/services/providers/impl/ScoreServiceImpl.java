package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;
import com.prolificidea.templates.tsw.services.DTOs.EntryDTO;
import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;
import com.prolificidea.templates.tsw.services.providers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by matthew.jordaan on 2016/03/01.
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    PersonService personService;

    @Autowired
    EntryService entryService;

    @Autowired
    TechnologyService technologyService;

    @Autowired
    ChallengeService challengeService;

    @Transactional
    public void addScore(EntryDTO entry) {
        List<EntryDTO> entries = entryService.findAllEntriesByPersonAndChallenge(entry.getPersonId(),entry.getChallengeId());
        PersonDTO p = personService.findPerson(entry.getPersonId());
        int score = 0;

        if (entries.size() == 1) {
            if (entry.getResult() != 0) {
                p.setScore(p.getScore() + entry.getResult());
                personService.updatePerson(p);
            }
        } else {
            if (entry.getResult() != 0) {
                int max = findMax(entries, entry);
                if (max <= entry.getResult()) {
                    System.out.println("max: " + max + " score: " + entry.getResult());
                    score = entry.getResult() - max;
                }
                if (score != 0) {
                    p.setScore(p.getScore() + score);
                    personService.updatePerson(p);
                }

            }

        }

    }

    private int findMax(List<EntryDTO> entries, EntryDTO entry) {
        int max = 0;

            for (EntryDTO e : entries) {
                System.out.println(e.getEntryId() + ": Res: " + e.getResult());
                if (e.getEntryId() != entry.getEntryId()) {
                    if (e.getResult() > max) max = e.getResult();
                }
            }
        System.out.println(max);
        return max;
    }

    @Transactional
    public void recalculateScores(PersonDTO personDTO) {
        List<EntryDTO> entries = entryService.getEntriesByPerson(personDTO.getPersonId());
        List<ChallengeDTO> challenges = challengeService.findAllChallenges();
        int i = 0;
        int sum = 0;
        for (ChallengeDTO c : challenges)
        {

            for (EntryDTO e : entries)
            {
                if (e.getChallengeId() == c.getChallengeId()){
                    sum += e.getResult();
                }
            }


            if (sum > 2) sum = 2;
            System.out.println("updated: " +sum + " i: " + i);



        }
        personDTO.setScore(sum/i);
        System.out.println(sum/i);
        personService.updatePerson(personDTO);
    }
}

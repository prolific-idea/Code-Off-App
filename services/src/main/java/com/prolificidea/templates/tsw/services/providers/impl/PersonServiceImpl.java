package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.domain.entities.Person;
import com.prolificidea.templates.tsw.domain.entities.Technology;
import com.prolificidea.templates.tsw.persistence.ChallengeDao;
import com.prolificidea.templates.tsw.persistence.PersonDao;
import com.prolificidea.templates.tsw.persistence.TechnologyDao;
import com.prolificidea.templates.tsw.services.DTOs.LeaderboardDTO;
import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;
import com.prolificidea.templates.tsw.services.DTOs.TechnologyDTO;
import com.prolificidea.templates.tsw.services.providers.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;

    @Autowired
    ChallengeDao challengeDao;

    @Autowired
    TechnologyDao technologyDao;


    public PersonDTO findPerson(Object id) {
        return new PersonDTO(personDao.find(id));
    }

    public List<PersonDTO> findAllPersons() {
        return convertDomainListToDtoList(personDao.findAll());
    }

    public List<PersonDTO> findAllPersons(int pageSize, int pageNumber) {
        return convertDomainListToDtoList(personDao.findAll(pageSize, pageNumber));
    }

    public List<PersonDTO> searchPersons(String property, String criteria) {
        return convertDomainListToDtoList(personDao.search(property, criteria));
    }

    public List<PersonDTO> searchPersons(String property, String criteria, int pageSize, int pageNumber) {
        return convertDomainListToDtoList(personDao.search(property, criteria, pageSize, pageNumber));
    }

    public long countPersons() {
        return personDao.count();
    }

    @Transactional
    public void deletePerson(Object id) {
        personDao.delete(id);
    }

    @Transactional
    public PersonDTO createPerson(PersonDTO t) {
        Person p = new Person();
        p.setFirstName(t.getFirstName());
        p.setLastName(t.getLastName());
        p.setScore(t.getScore());
        p.setUsername(t.getUsername());
        p.setUrl(t.getUrl());
        return new PersonDTO(personDao.create(p));
    }

    @Transactional
    public PersonDTO updatePerson(PersonDTO t) {
        Person p = personDao.find(t.getPersonId());
        p.setFirstName(t.getFirstName());
        p.setLastName(t.getLastName());
        p.setScore(t.getScore());
        p.setUsername(t.getUsername());
        p.setUrl(t.getUrl());
        return new PersonDTO(personDao.update(p));
    }


    public List<PersonDTO> findAllPersonsDesc(int pageSize, int pageNumber) {
        return convertDomainListToDtoList(personDao.findAllPersonsDesc(pageSize, pageNumber));
    }

    public List<PersonDTO> findAllPersonsDesc() {
        return convertDomainListToDtoList(personDao.findAllPersonsDesc());
    }

    public List<LeaderboardDTO> getScoresByChallenge(int id) {
        Challenge challenge = challengeDao.find(id);
        return convertPersonListToLeaderboardList(convertDomainListToDtoList(personDao.getScoresByChallenge(challenge)));
    }

    public List<LeaderboardDTO> getScoresByTech(int id) {
        Technology technology = technologyDao.find(id);
        return convertPersonListToLeaderboardList(convertDomainListToDtoList(personDao.getScoresByTech(technology)));
    }

    public int getNoCodeOffs(PersonDTO person) {
        return personDao.getNoCodeOffs(person.getPersonId());
    }

    public List<TechnologyDTO> getListOfTechsByPerson(int id) {
        Person person = personDao.find(id);
        List<Technology> techs = personDao.getListOfTechs(person);
        List<TechnologyDTO> techDtos = new ArrayList<TechnologyDTO>();
        for (Technology t : techs) {
            techDtos.add(new TechnologyDTO(t));
        }
        return techDtos;
    }

    public List<LeaderboardDTO> getLeaderboard() {
        List<PersonDTO> personDTOs = convertDomainListToDtoList(personDao.findAllPersonsDesc());
        return convertPersonListToLeaderboardList(personDTOs);
    }

    public List<LeaderboardDTO> getLeaderboard(int pageSize, int pageNum) {
        List<PersonDTO> personDTOs = convertDomainListToDtoList(personDao.findAllPersonsDesc(pageSize, pageNum));
        return convertPersonListToLeaderboardList(personDTOs);
    }

    private List<LeaderboardDTO> convertPersonListToLeaderboardList(List<PersonDTO> persons) {
        List<LeaderboardDTO> leaders = new ArrayList<LeaderboardDTO>();
        for (PersonDTO p : persons) {
            leaders.add(new LeaderboardDTO(p, getNoCodeOffs(p), getListOfTechsByPerson(p.getPersonId())));
        }
        return leaders;
    }

    private List<PersonDTO> convertDomainListToDtoList(List<Person> persons) {
        List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();
        for (Person p : persons) {
            personDTOs.add(new PersonDTO(p));

        }
        return personDTOs;
    }

}

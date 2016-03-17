package com.prolificidea.templates.tsw.persistence;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.domain.entities.Person;
import com.prolificidea.templates.tsw.domain.entities.Technology;
import com.prolificidea.templates.tsw.persistence.generic.GenericDao;

import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
public interface PersonDao extends GenericDao<Person> {

    List<Person> findAllPersonsDesc(int pageSize, int pageNum);
    List<Person> findAllPersonsDesc();
    List<Person> getScoresByChallenge(Challenge challenge);
    List<Person> getScoresByTech(List<Technology> technology);
    List<Person> getScoresByTech(List<Technology> technology, int pageSize, int pageNum);
    int getNoCodeOffs(Integer personId);
    List<Technology> getListOfTechs(Person person);
    List<Person> getScoresByChallenge(Challenge challenge, int pageSize, int pageNum);


    }

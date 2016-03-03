package com.prolificidea.templates.tsw.persistence;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.domain.entities.Person;
import com.prolificidea.templates.tsw.persistence.generic.GenericDao;

import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
public interface EntryDao extends GenericDao<Entry> {

    List<Entry> findAllEntriesByPerson(Person p);
    List<Entry> findAllEntriesByPersonAndChallenge(Person p, Challenge c);
}

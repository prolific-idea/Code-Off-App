package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.domain.entities.Person;

import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
public interface PersonService {

    Person findPerson(Object id);
    List<Person> findAllPersons();
    List<Person> findAllPersons(int pageSize, int pageNumber);
    List<Person> searchPersons(String property, String criteria);
    List<Person> searchPersons(String property, String criteria, int pageSize, int pageNumber);
    long countPersons();
    void deletePerson(Object id);
    Person createPerson(Person t);
    Person updatePerson(Person t);
}

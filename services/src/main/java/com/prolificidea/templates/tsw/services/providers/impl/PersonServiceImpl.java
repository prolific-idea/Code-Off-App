package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Person;
import com.prolificidea.templates.tsw.persistence.PersonDao;
import com.prolificidea.templates.tsw.services.providers.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.ServiceMode;
import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonDao personDao;

    public Person findPerson(Object id) {
        return personDao.find(id);
    }

    public List<Person> findAllPersons() {
        return personDao.findAll();
    }

    public List<Person> findAllPersons(int pageSize, int pageNumber) {
        return personDao.findAll(pageSize, pageNumber);
    }

    public List<Person> searchPersons(String property, String criteria) {
        return personDao.search(property, criteria);
    }

    public List<Person> searchPersons(String property, String criteria, int pageSize, int pageNumber) {
        return personDao.search(property, criteria, pageSize, pageNumber);
    }

    public long countPersons() {
        return personDao.count();
    }

    @Transactional
    public void deletePerson(Object id) {
        personDao.delete(id);
    }

    @Transactional
    public Person createPerson(Person t) {
        return personDao.create(t);
    }

    @Transactional
    public Person updatePerson(Person t) {
        return personDao.update(t);
    }
}

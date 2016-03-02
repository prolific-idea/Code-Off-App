package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.domain.entities.Person;
import com.prolificidea.templates.tsw.persistence.EntryDao;
import com.prolificidea.templates.tsw.persistence.PersonDao;
import com.prolificidea.templates.tsw.services.providers.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    EntryDao entryDao;

    @Autowired
    PersonDao personDao;
    public Entry findEntry(Object id) {
        return entryDao.find(id);
    }

    public List<Entry> findAllEntrys() {
        return entryDao.findAll();
    }

    public List<Entry> findAllEntrys(int pageSize, int pageNumber) {
        return entryDao.findAll(pageSize, pageNumber);
    }

    public List<Entry> searchEntrys(String property, String criteria) {
        return entryDao.search(property, criteria);
    }

    public List<Entry> searchEntrys(String property, String criteria, int pageSize, int pageNumber) {
        return entryDao.search(property, criteria, pageSize, pageNumber);
    }

    public long countEntrys() {
        return entryDao.count();
    }

    @Transactional
    public void deleteEntry(Object id) {
        entryDao.delete(id);
    }

    @Transactional
    public Entry createEntry(Entry t) {
        return entryDao.create(t);
    }

    @Transactional
    public Entry updateEntry(Entry t) {
        return entryDao.update(t);
    }

    public List<Entry> getEntriesByPerson(int id) {
        Person p = personDao.find(id);
        return entryDao.findAllEntriesByPerson(p);
    }
}

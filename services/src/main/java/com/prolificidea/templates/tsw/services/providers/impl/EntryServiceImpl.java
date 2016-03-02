package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.domain.entities.Person;
import com.prolificidea.templates.tsw.persistence.ChallengeDao;
import com.prolificidea.templates.tsw.persistence.EntryDao;
import com.prolificidea.templates.tsw.persistence.PersonDao;
import com.prolificidea.templates.tsw.persistence.TechnologyDao;
import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;
import com.prolificidea.templates.tsw.services.DTOs.EntryDTO;
import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;
import com.prolificidea.templates.tsw.services.providers.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    ChallengeDao challengeDao;

    @Autowired
    TechnologyDao technologyDao;

    public EntryDTO findEntry(Object id) {
        return new EntryDTO(entryDao.find(id));
    }

    public List<EntryDTO> findAllEntrys() {
        return convertDomainListToDtoList(entryDao.findAll());
    }

    public List<EntryDTO> findAllEntrys(int pageSize, int pageNumber) {
        return convertDomainListToDtoList(entryDao.findAll(pageSize, pageNumber));
    }

    public List<EntryDTO> searchEntrys(String property, String criteria) {
        return convertDomainListToDtoList(entryDao.search(property, criteria));
    }

    public List<EntryDTO> searchEntrys(String property, String criteria, int pageSize, int pageNumber) {
        return convertDomainListToDtoList(entryDao.search(property, criteria, pageSize, pageNumber));
    }

    public long countEntrys() {
        return entryDao.count();
    }

    @Transactional
    public void deleteEntry(Object id) {
        entryDao.delete(id);
    }

    @Transactional
    public EntryDTO createEntry(EntryDTO t) {
        Entry e = new Entry();
        e.setChallengeId(challengeDao.find(t.getChallengeId()));
        e.setDate(t.getDate());
        e.setPersonId(personDao.find(t.getPersonId()));
        e.setResult(t.getResult());
        e.setSolution(t.getSolution());
        e.setTechId(technologyDao.find(t.getTechId()));
        e.setUrl(t.getUrl());
        return new EntryDTO(entryDao.create(e));
    }

    @Transactional
    public EntryDTO updateEntry(EntryDTO t) {
        Entry e = entryDao.find(t.getEntryId());
        e.setChallengeId(challengeDao.find(t.getChallengeId()));
        e.setDate(t.getDate());
        e.setPersonId(personDao.find(t.getPersonId()));
        e.setResult(t.getResult());
        e.setSolution(t.getSolution());
        e.setTechId(technologyDao.find(t.getTechId()));
        e.setUrl(t.getUrl());
        return new EntryDTO(entryDao.update(e));
    }

    public List<EntryDTO> getEntriesByPerson(int id) {
        Person p = personDao.find(id);
        return convertDomainListToDtoList(entryDao.findAllEntriesByPerson(p));
    }

    public List<EntryDTO> findAllEntriesByPersonAndChallenge(int personId, int challengeId) {
        Person person = personDao.find(personId);
        Challenge challenge = challengeDao.find(challengeId);
        return convertDomainListToDtoList(entryDao.findAllEntriesByPersonAndChallenge(person,challenge));
    }

    private List<EntryDTO> convertDomainListToDtoList(List<Entry> entrys) {
        List<EntryDTO> entryDTOs = new ArrayList<EntryDTO>();
        for (Entry e : entrys)
        {
            entryDTOs.add(new EntryDTO(e));

        }
        return entryDTOs;
    }
}

package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;

import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
public interface PersonService {

    PersonDTO findPerson(Object id);
    List<PersonDTO> findAllPersons();
    List<PersonDTO> findAllPersons(int pageSize, int pageNumber);
    List<PersonDTO> findAllPersonsDesc(int pageSize, int pageNumber);
    List<PersonDTO> findAllPersonsDesc();
    List<PersonDTO> searchPersons(String property, String criteria);
    List<PersonDTO> searchPersons(String property, String criteria, int pageSize, int pageNumber);
    long countPersons();
    void deletePerson(Object id);
    PersonDTO createPerson(PersonDTO t);
    PersonDTO updatePerson(PersonDTO t);
    List<PersonDTO> getScoresByChallenge(int id);
    List<PersonDTO> getScoresByTech(int id);
}

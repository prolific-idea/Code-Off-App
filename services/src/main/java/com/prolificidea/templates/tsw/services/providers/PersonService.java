package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.services.DTOs.LeaderboardDTO;
import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;
import com.prolificidea.templates.tsw.services.DTOs.TechnologyDTO;

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
    List<LeaderboardDTO> getScoresByChallenge(int codeOffNumber);
    List<LeaderboardDTO> getScoresByTech(String techName);
    List<LeaderboardDTO> getScoresByChallenge(int codeOffNumber, int pageSize, int pageNum);
    List<LeaderboardDTO> getScoresByTech(String techName, int pageSize, int pageNum);
    int getNoCodeOffs(PersonDTO person);
    List<TechnologyDTO> getListOfTechsByPerson(int id);
    List<LeaderboardDTO> getLeaderboard();
    List<LeaderboardDTO> getLeaderboard(int pageSize, int pageNum);

    }

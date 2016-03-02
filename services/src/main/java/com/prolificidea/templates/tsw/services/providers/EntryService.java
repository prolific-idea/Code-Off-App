package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;
import com.prolificidea.templates.tsw.services.DTOs.EntryDTO;
import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;

import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
public interface EntryService {

    EntryDTO findEntry(Object id);
    List<EntryDTO> findAllEntrys();
    List<EntryDTO> findAllEntrys(int pageSize, int pageNumber);
    List<EntryDTO> searchEntrys(String property, String criteria);
    List<EntryDTO> searchEntrys(String property, String criteria, int pageSize, int pageNumber);
    long countEntrys();
    void deleteEntry(Object id);
    EntryDTO createEntry(EntryDTO t);
    EntryDTO updateEntry(EntryDTO t);
    List<EntryDTO> getEntriesByPerson(int id);
    List<EntryDTO> findAllEntriesByPersonAndChallenge(int personId, int challengeId);
}

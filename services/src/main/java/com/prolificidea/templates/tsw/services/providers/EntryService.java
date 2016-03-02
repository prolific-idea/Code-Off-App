package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.domain.entities.Entry;

import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
public interface EntryService {

    Entry findEntry(Object id);
    List<Entry> findAllEntrys();
    List<Entry> findAllEntrys(int pageSize, int pageNumber);
    List<Entry> searchEntrys(String property, String criteria);
    List<Entry> searchEntrys(String property, String criteria, int pageSize, int pageNumber);
    long countEntrys();
    void deleteEntry(Object id);
    Entry createEntry(Entry t);
    Entry updateEntry(Entry t);
    List<Entry> getEntriesByPerson(int id);
}

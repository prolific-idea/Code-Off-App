package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.domain.entities.Technology;

import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
public interface TechnologyService {

    Technology findTechnology(Object id);
    List<Technology> findAllTechnologys();
    List<Technology> findAllTechnologys(int pageSize, int pageNumber);
    List<Technology> searchTechnologys(String property, String criteria);
    List<Technology> searchTechnologys(String property, String criteria, int pageSize, int pageNumber);
    long countTechnologys();
    void deleteTechnology(Object id);
    Technology createTechnology(Technology t);
    Technology updateTechnology(Technology t);
}

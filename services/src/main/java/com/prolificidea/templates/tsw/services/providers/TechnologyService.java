package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.services.DTOs.TechnologyDTO;

import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
public interface TechnologyService {

    TechnologyDTO findTechnology(Object id);
    List<TechnologyDTO> findAllTechnologys();
    List<TechnologyDTO> findAllTechnologys(int pageSize, int pageNumber);
    List<TechnologyDTO> searchTechnologys(String property, String criteria);
    List<TechnologyDTO> searchTechnologys(String property, String criteria, int pageSize, int pageNumber);
    long countTechnologys();
    void deleteTechnology(Object id);
    TechnologyDTO createTechnology(TechnologyDTO t);
    TechnologyDTO updateTechnology(TechnologyDTO t);
}

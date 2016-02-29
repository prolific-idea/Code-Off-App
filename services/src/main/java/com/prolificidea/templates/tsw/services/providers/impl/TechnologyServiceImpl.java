package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Technology;
import com.prolificidea.templates.tsw.persistence.TechnologyDao;
import com.prolificidea.templates.tsw.services.providers.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    TechnologyDao technologyDao;

    public Technology findTechnology(Object id) {
        return technologyDao.find(id);
    }

    public List<Technology> findAllTechnologys() {
        return technologyDao.findAll();
    }

    public List<Technology> findAllTechnologys(int pageSize, int pageNumber) {
        return technologyDao.findAll(pageSize, pageNumber);
    }

    public List<Technology> searchTechnologys(String property, String criteria) {
        return technologyDao.search(property, criteria);
    }

    public List<Technology> searchTechnologys(String property, String criteria, int pageSize, int pageNumber) {
        return technologyDao.search(property, criteria, pageSize, pageNumber);
    }

    public long countTechnologys() {
        return technologyDao.count();
    }

    @Transactional
    public void deleteTechnology(Object id) {
        technologyDao.delete(id);
    }

    @Transactional
    public Technology createTechnology(Technology t) {
        return technologyDao.create(t);
    }

    @Transactional
    public Technology updateTechnology(Technology t) {
        return technologyDao.update(t);
    }
}

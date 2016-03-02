package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Technology;
import com.prolificidea.templates.tsw.persistence.TechnologyDao;
import com.prolificidea.templates.tsw.services.DTOs.TechnologyDTO;
import com.prolificidea.templates.tsw.services.providers.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    TechnologyDao technologyDao;

    public TechnologyDTO findTechnology(Object id) {
        return new TechnologyDTO(technologyDao.find(id));
    }

    public List<TechnologyDTO> findAllTechnologys() {
        return convertDomainListToDtoList(technologyDao.findAll());
    }

    public List<TechnologyDTO> findAllTechnologys(int pageSize, int pageNumber) {
        return convertDomainListToDtoList(technologyDao.findAll(pageSize, pageNumber));
    }

    public List<TechnologyDTO> searchTechnologys(String property, String criteria) {

        return convertDomainListToDtoList(technologyDao.search(property, criteria));
    }

    public List<TechnologyDTO> searchTechnologys(String property, String criteria, int pageSize, int pageNumber) {
        return convertDomainListToDtoList(technologyDao.search(property, criteria, pageSize, pageNumber));
    }

    public long countTechnologys() {
        return technologyDao.count();
    }

    @Transactional
    public void deleteTechnology(Object id) {
        technologyDao.delete(id);
    }

    @Transactional
    public TechnologyDTO createTechnology(TechnologyDTO t) {
        Technology newTech = new Technology();
        newTech.setDescription(t.getDescription());
        return new TechnologyDTO(technologyDao.create(newTech));
    }

    @Transactional
    public TechnologyDTO updateTechnology(TechnologyDTO t) {
        Technology newTech = technologyDao.find(t.getTechId());
        newTech.setDescription(t.getDescription());
        return new TechnologyDTO(technologyDao.update(newTech));
    }

    private List<TechnologyDTO> convertDomainListToDtoList(List<Technology> techs) {
        List<TechnologyDTO> techDTOs = new ArrayList<TechnologyDTO>();
        for (Technology t : techs)
        {
            techDTOs.add(new TechnologyDTO(t));

        }
        return techDTOs;
    }
}

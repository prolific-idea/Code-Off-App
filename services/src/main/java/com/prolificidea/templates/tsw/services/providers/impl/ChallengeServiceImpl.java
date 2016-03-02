package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.persistence.ChallengeDao;
import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;
import com.prolificidea.templates.tsw.services.providers.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    ChallengeDao challengeDao;

    public ChallengeDTO findChallenge(Object id) {
        return new ChallengeDTO(challengeDao.find(id));
    }

    public List<ChallengeDTO> findAllChallenges() {
        return convertDomainListToDtoList(challengeDao.findAll());
    }

    public List<ChallengeDTO> findAllChallenges(int pageSize, int pageNumber) {
        return convertDomainListToDtoList(challengeDao.findAll(pageSize, pageNumber));
    }

    public List<ChallengeDTO> searchChallenges(String property, String criteria) {
        return convertDomainListToDtoList(challengeDao.search(property,criteria));
    }

    public List<ChallengeDTO> searchChallenges(String property, String criteria, int pageSize, int pageNumber) {
        return convertDomainListToDtoList(challengeDao.search(property, criteria, pageSize, pageNumber));
    }

    public long countChallenges() {
        return challengeDao.count();
    }

    @Transactional
    public void deleteChallenge(Object id) {
        challengeDao.delete(id);
    }

    @Transactional
    public ChallengeDTO createChallenge(ChallengeDTO t) {
        Challenge c = new Challenge();
        c.setUrl(t.getUrl());
        c.setEndDate(t.getEndDate());
        c.setNumberOfLinesToCompare(t.getNumberOfLinesToCompare());
        c.setSolution(t.getSolution());
        c.setSolutionFilePath(t.getSolutionFilePath());
        c.setStartDate(t.getStartDate());

        return new ChallengeDTO(challengeDao.create(c));
    }

    @Transactional
    public ChallengeDTO updateChallenge(ChallengeDTO t) {
        Challenge c = challengeDao.find(t.getChallengeId());
        c.setUrl(t.getUrl());
        c.setEndDate(t.getEndDate());
        c.setNumberOfLinesToCompare(t.getNumberOfLinesToCompare());
        c.setSolution(t.getSolution());
        c.setSolutionFilePath(t.getSolutionFilePath());
        c.setStartDate(t.getStartDate());
        return new ChallengeDTO(challengeDao.update(c));
    }

    private List<ChallengeDTO> convertDomainListToDtoList(List<Challenge> challenges) {
        List<ChallengeDTO> challengeDTOs = new ArrayList<ChallengeDTO>();
        for (Challenge c : challenges)
        {
            challengeDTOs.add(new ChallengeDTO(c));

        }
        return challengeDTOs;
    }
}

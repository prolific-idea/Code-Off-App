package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.persistence.ChallengeDao;
import com.prolificidea.templates.tsw.services.providers.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    ChallengeDao challengeDao;

    public Challenge findChallenge(Object id) {
        return challengeDao.find(id);
    }

    public List<Challenge> findAllChallenges() {
        return challengeDao.findAll();
    }

    public List<Challenge> findAllChallenges(int pageSize, int pageNumber) {
        return challengeDao.findAll(pageSize, pageNumber);
    }

    public List<Challenge> searchChallenges(String property, String criteria) {
        return challengeDao.search(property,criteria);
    }

    public List<Challenge> searchChallenges(String property, String criteria, int pageSize, int pageNumber) {
        return challengeDao.search(property, criteria, pageSize, pageNumber);
    }

    public long countChallenges() {
        return challengeDao.count();
    }

    @Transactional
    public void deleteChallenge(Object id) {
        challengeDao.delete(id);
    }

    @Transactional
    public Challenge createChallenge(Challenge t) {
        return challengeDao.create(t);
    }

    @Transactional
    public Challenge updateChallenge(Challenge t) {
        return challengeDao.update(t);
    }
}

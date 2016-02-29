package com.prolificidea.templates.tsw.services.providers;


import com.prolificidea.templates.tsw.domain.entities.Challenge;

import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
public interface ChallengeService {

    Challenge findChallenge(Object id);
    List<Challenge> findAllChallenges();
    List<Challenge> findAllChallenges(int pageSize, int pageNumber);
    List<Challenge> searchChallenges(String property, String criteria);
    List<Challenge> searchChallenges(String property, String criteria, int pageSize, int pageNumber);
    long countChallenges();
    void deleteChallenge(Object id);
    Challenge createChallenge(Challenge t);
    Challenge updateChallenge(Challenge t);
}

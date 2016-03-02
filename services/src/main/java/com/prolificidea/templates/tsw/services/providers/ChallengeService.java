package com.prolificidea.templates.tsw.services.providers;


import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;

import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
public interface ChallengeService {

    ChallengeDTO findChallenge(Object id);
    List<ChallengeDTO> findAllChallenges();
    List<ChallengeDTO> findAllChallenges(int pageSize, int pageNumber);
    List<ChallengeDTO> searchChallenges(String property, String criteria);
    List<ChallengeDTO> searchChallenges(String property, String criteria, int pageSize, int pageNumber);
    long countChallenges();
    void deleteChallenge(Object id);
    ChallengeDTO createChallenge(ChallengeDTO t);
    ChallengeDTO updateChallenge(ChallengeDTO t);
}

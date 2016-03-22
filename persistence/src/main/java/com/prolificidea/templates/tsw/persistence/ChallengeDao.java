package com.prolificidea.templates.tsw.persistence;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.persistence.generic.GenericDao;

import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
public interface ChallengeDao extends GenericDao<Challenge> {
    Challenge findByCodeOffNumber(int codeOffNumber);
    List<Challenge> findAll(int pageSize, int pageNumber);
}

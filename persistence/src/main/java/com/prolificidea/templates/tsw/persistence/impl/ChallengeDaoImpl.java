package com.prolificidea.templates.tsw.persistence.impl;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.persistence.ChallengeDao;
import com.prolificidea.templates.tsw.persistence.generic.GenericDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Repository
public class ChallengeDaoImpl extends GenericDaoImpl<Challenge> implements ChallengeDao {
    public Challenge findByCodeOffNumber(int codeOffNumber) {
        List<Challenge> challenges = this.search("codeOffNumber", "" + codeOffNumber);
        return challenges.get(0);
    }
}

package com.prolificidea.templates.tsw.persistence.impl;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.persistence.ChallengeDao;
import com.prolificidea.templates.tsw.persistence.generic.GenericDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Repository
public class ChallengeDaoImpl extends GenericDaoImpl<Challenge> implements ChallengeDao {
}

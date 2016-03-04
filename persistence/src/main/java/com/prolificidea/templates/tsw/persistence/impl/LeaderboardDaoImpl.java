package com.prolificidea.templates.tsw.persistence.impl;

import com.prolificidea.templates.tsw.domain.entities.Leaderboard;
import com.prolificidea.templates.tsw.persistence.LeaderboardDao;
import com.prolificidea.templates.tsw.persistence.generic.GenericDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by matthew.jordaan on 2016/03/03.
 */
@Repository
public class LeaderboardDaoImpl  extends GenericDaoImpl<Leaderboard> implements LeaderboardDao {
}

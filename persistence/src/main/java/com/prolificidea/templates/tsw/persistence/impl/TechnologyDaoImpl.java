package com.prolificidea.templates.tsw.persistence.impl;

import com.prolificidea.templates.tsw.domain.entities.Technology;
import com.prolificidea.templates.tsw.persistence.TechnologyDao;
import com.prolificidea.templates.tsw.persistence.generic.GenericDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Repository
public class TechnologyDaoImpl extends GenericDaoImpl<Technology> implements TechnologyDao {
}

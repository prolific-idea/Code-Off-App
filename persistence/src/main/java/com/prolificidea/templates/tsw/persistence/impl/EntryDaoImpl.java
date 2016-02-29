package com.prolificidea.templates.tsw.persistence.impl;

import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.persistence.EntryDao;
import com.prolificidea.templates.tsw.persistence.generic.GenericDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Repository
public class EntryDaoImpl extends GenericDaoImpl<Entry> implements EntryDao {
}

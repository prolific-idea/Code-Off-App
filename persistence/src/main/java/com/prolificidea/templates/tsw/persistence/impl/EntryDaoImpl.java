package com.prolificidea.templates.tsw.persistence.impl;

import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.domain.entities.Person;
import com.prolificidea.templates.tsw.persistence.EntryDao;
import com.prolificidea.templates.tsw.persistence.generic.GenericDaoImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Repository
public class EntryDaoImpl extends GenericDaoImpl<Entry> implements EntryDao {


    public List<Entry> findAllEntriesByPerson(Person p) {
        Query q = this.entityManager.createQuery("SELECT x from Entry x where x.personId like :pId").setParameter("pId", p);
        return q.getResultList();
    }
}

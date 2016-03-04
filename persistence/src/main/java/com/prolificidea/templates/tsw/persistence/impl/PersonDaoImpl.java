package com.prolificidea.templates.tsw.persistence.impl;

import com.prolificidea.templates.tsw.domain.entities.Person;
import com.prolificidea.templates.tsw.persistence.PersonDao;
import com.prolificidea.templates.tsw.persistence.generic.GenericDaoImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Repository
public class PersonDaoImpl extends GenericDaoImpl<Person> implements PersonDao {

    public List<Person> findAllPersonsDesc(int pageSize, int pageNum) {
            Query query = this.entityManager.createQuery("SELECT x from Person x ORDER BY x.score DESC");
            query.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize);
            return query.getResultList();
        }

    public List<Person> findAllPersonsDesc() {
        Query query = this.entityManager.createQuery("SELECT x from Person x ORDER BY x.score DESC");
        return query.getResultList();
    }

}

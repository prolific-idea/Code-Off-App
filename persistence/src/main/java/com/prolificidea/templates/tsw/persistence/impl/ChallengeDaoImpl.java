package com.prolificidea.templates.tsw.persistence.impl;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.persistence.ChallengeDao;
import com.prolificidea.templates.tsw.persistence.generic.GenericDaoImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Repository
public class ChallengeDaoImpl extends GenericDaoImpl<Challenge> implements ChallengeDao {
    public Challenge findByCodeOffNumber(int codeOffNumber) {
        List<Challenge> challenges = this.search("codeOffNumber", "" + codeOffNumber);
        if (challenges.size() > 0)
            return challenges.get(0);

        return null;
    }

    @Override
    public List<Challenge> search(String property, String criteria) {
        StringBuilder sb = new StringBuilder();
        sb.append("select x from ");
        sb.append(" Challenge x ");
        sb.append(" where x.");
        sb.append(property);
        sb.append(" like '%");
        sb.append(criteria);
        sb.append("%' AND x.isDeleted = false");
        Query query = this.entityManager.createQuery(sb.toString());
        return query.getResultList();
    }

    @Override
    public long count() {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(x) from ");
        sb.append(" Challenge x");
        sb.append(" where x.isDeleted = false");
        sb.append(" order by x.codeOffNumber");
        Query query = this.entityManager.createQuery(sb.toString());
        return (Long)query.getSingleResult();
    }

    @Override
    public List<Challenge> findAll(int pageSize, int pageNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("select x from ");
        sb.append(" Challenge x");
        sb.append(" where x.isDeleted = false");
        sb.append(" order by x.codeOffNumber");
        Query query = this.entityManager.createQuery(sb.toString());
        query.setFirstResult((pageNumber - 1) * pageSize).setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<Challenge> findAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("select x from ");
        sb.append(" Challenge x");
        sb.append(" where x.isDeleted = false");
        sb.append(" order by x.codeOffNumber");
        Query query = this.entityManager.createQuery(sb.toString());
        return query.getResultList();
    }

    @Override
    public void delete(Object id) {
        Challenge challenge = this.find(id);
        challenge.setDeleted(true);
        this.update(challenge);
    }
}

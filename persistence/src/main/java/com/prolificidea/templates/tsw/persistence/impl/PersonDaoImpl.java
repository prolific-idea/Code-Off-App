package com.prolificidea.templates.tsw.persistence.impl;

import com.prolificidea.templates.tsw.domain.entities.Challenge;
import com.prolificidea.templates.tsw.domain.entities.Person;
import com.prolificidea.templates.tsw.domain.entities.Technology;
import com.prolificidea.templates.tsw.persistence.PersonDao;
import com.prolificidea.templates.tsw.persistence.generic.GenericDaoImpl;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sahil.naran on 2016/02/29.
 */
@Repository
public class PersonDaoImpl extends GenericDaoImpl<Person> implements PersonDao {

    public List<Person> findAllPersonsDesc(int pageSize, int pageNum) {
        Query query = this.entityManager.createQuery("SELECT x  from Person x ORDER BY x.actualScore  DESC");
        query.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize);
        return query.getResultList();
    }

    public List<Person> findAllPersonsDesc() {
        Query query = this.entityManager.createQuery("SELECT x from Person x ORDER BY x.score DESC");
        return query.getResultList();
    }

    public List<Person> getScoresByChallenge(Challenge challenge) {
        Query query = this.entityManager.createQuery("Select distinct p " +
                "from Entry e, Person p where p.personId = e.personId and e.challengeId = :chal" +
                " order by p.score desc").setParameter("chal", challenge);
        List<Person> peoples = query.getResultList();
        return peoples;
    }

    public List<Person> getScoresByTech(List<Technology> technologies) {
        Set<Person> peoples = new HashSet<Person>();
        for (Technology technology : technologies) {
            Query query = this.entityManager.createQuery("Select distinct p " +
                    "from Entry e, Person p where p.personId = e.personId and e.techId = :tech" +
                    " order by p.score desc").setParameter("tech", technologies);
            peoples.addAll(query.getResultList());
        }

        return new ArrayList<Person>(peoples);
    }

    public List<Person> getScoresByChallenge(Challenge challenge, int pageSize, int pageNum) {
        Query query = this.entityManager.createQuery("Select distinct p " +
                "from Entry e, Person p where p.personId = e.personId and e.challengeId = :chal" +
                " order by p.score desc").setParameter("chal", challenge);
        query.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize);
        List<Person> peoples = query.getResultList();
        return peoples;
    }

    public List<Person> getScoresByTech(List<Technology> technologies, int pageSize, int pageNum) {
        Set<Person> peoples = new HashSet<Person>();
        for (Technology technology : technologies) {
            Query query = this.entityManager.createQuery("Select distinct p " +
                    "from Entry e, Person p where p.personId = e.personId and e.techId = :tech" +
                    " order by p.score desc").setParameter("tech", technology);
            query.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize);
            peoples.addAll(query.getResultList());
        }

        return new ArrayList<Person>(peoples);
    }

    public int getNoCodeOffs(Integer personId) {
        Query query = this.entityManager.createQuery("select count(distinct e.challengeId)" +
                " from Entry e where e.personId.personId = :per ").setParameter("per", personId);
        return Integer.parseInt(query.getSingleResult().toString());
    }

    public List<Technology> getListOfTechs(Person person) {
        Query query = this.entityManager.createQuery(
                "select distinct t from Person p, Entry e, Technology t " +
                        "where p.personId = e.personId and t.techId = e.techId " +
                        "and p = :per"
        ).setParameter("per", person);
        return query.getResultList();
    }

}

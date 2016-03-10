package com.prolificidea.templates.tsw.persistence.impl;

import com.prolificidea.templates.tsw.domain.entities.AppUser;
import com.prolificidea.templates.tsw.persistence.generic.GenericDaoImpl;
import com.prolificidea.templates.tsw.persistence.AppUserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class AppUserDaoImpl extends GenericDaoImpl<AppUser> implements AppUserDao {

    public AppUser getAppUserByUsername(String username) {
        Query query = this.entityManager.createQuery(
                "select x from AppUser x where x.username = :username"
        );
        query.setParameter("username", username);
        List<AppUser> results = query.getResultList();
        if(results.size() > 0)
            return results.get(0);
        return null;
    }

}

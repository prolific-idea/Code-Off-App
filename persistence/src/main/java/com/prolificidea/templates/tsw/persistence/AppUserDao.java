package com.prolificidea.templates.tsw.persistence;

import com.prolificidea.templates.tsw.domain.AppUser;
import com.prolificidea.templates.tsw.persistence.generic.GenericDao;

public interface AppUserDao extends GenericDao<AppUser> {

    public AppUser getAppUserByUsername(String username);

}
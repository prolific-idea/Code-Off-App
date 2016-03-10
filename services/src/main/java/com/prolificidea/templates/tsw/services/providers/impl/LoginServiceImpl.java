package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.AppUser;
import com.prolificidea.templates.tsw.persistence.AppUserDao;
import com.prolificidea.templates.tsw.services.providers.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by matthew.jordaan on 2016/03/10.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AppUserDao appUserDao;

    public AppUser getUserByUsername(String username) {
        return appUserDao.getAppUserByUsername(username);
    }
}

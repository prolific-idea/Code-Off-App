package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.AppUser;
import com.prolificidea.templates.tsw.persistence.AppUserDao;
import com.prolificidea.templates.tsw.services.providers.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserDao appUserDao;

    public AppUser getAppUserByAppUserId(int id) {
        return appUserDao.find(id);
    }

    public List<AppUser> getAllAppUser() {
        return appUserDao.findAll();
    }

    public List<AppUser> getAllAppUser(int startIndex, int numberOfRows) {
        return appUserDao.findAll(numberOfRows, startIndex);
    }

    public List<AppUser> searchAppUser(String property, String searchCriteria) {
        return appUserDao.search(property, searchCriteria);
    }

    public List<AppUser> searchAppUser(String property, String searchCriteria, int startIndex, int numberOfRows) {
        return appUserDao.search(property, searchCriteria, numberOfRows, startIndex);
    }

    public long countAppUser() {
        return appUserDao.count();
    }

    @Transactional
    public AppUser createAppUser(AppUser object) {
        return appUserDao.create(object);
    }

    @Transactional
    public AppUser updateAppUser(AppUser object) {
        return appUserDao.update(object);
    }

}

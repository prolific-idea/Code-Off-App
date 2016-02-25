package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.AppUserDetails;
import com.prolificidea.templates.tsw.persistence.AppUserDetailsDao;
import com.prolificidea.templates.tsw.services.providers.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppUserDetailsServiceImpl implements AppUserDetailsService {

    @Autowired
    private AppUserDetailsDao appUserDetailsDao;

    public AppUserDetails getAppUserDetailsByAppUserDetailsId(int id) {
        return appUserDetailsDao.find(id);
    }

    public List<AppUserDetails> getAllAppUserDetails() {
        return appUserDetailsDao.findAll();
    }

    public List<AppUserDetails> getAllAppUserDetails(int startIndex, int numberOfRows) {
        return appUserDetailsDao.findAll(numberOfRows, startIndex);
    }

    public List<AppUserDetails> searchAppUserDetails(String property, String searchCriteria) {
        return appUserDetailsDao.search(property, searchCriteria);
    }

    public List<AppUserDetails> searchAppUserDetails(String property, String searchCriteria, int startIndex, int numberOfRows) {
        return appUserDetailsDao.search(property, searchCriteria, numberOfRows, startIndex);
    }

    public long countAppUserDetails() {
        return appUserDetailsDao.count();
    }

    @Transactional
    public AppUserDetails createAppUserDetails(AppUserDetails object) {
        return appUserDetailsDao.create(object);
    }

    @Transactional
    public AppUserDetails updateAppUserDetails(AppUserDetails object) {
        return appUserDetailsDao.update(object);
    }

}

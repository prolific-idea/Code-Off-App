package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.AppUserRole;
import com.prolificidea.templates.tsw.persistence.AppUserRoleDao;
import com.prolificidea.templates.tsw.services.providers.AppUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppUserRoleServiceImpl implements AppUserRoleService {

    @Autowired
    private AppUserRoleDao appUserRoleDao;

    public AppUserRole getAppUserRoleByAppUserRoleId(int id) {
        return appUserRoleDao.find(id);
    }

    public List<AppUserRole> getAllAppUserRole() {
        return appUserRoleDao.findAll();
    }

    public List<AppUserRole> getAllAppUserRole(int startIndex, int numberOfRows) {
        return appUserRoleDao.findAll(numberOfRows, startIndex);
    }

    public List<AppUserRole> searchAppUserRole(String property, String searchCriteria) {
        return appUserRoleDao.search(property, searchCriteria);
    }

    public List<AppUserRole> searchAppUserRole(String property, String searchCriteria, int startIndex, int numberOfRows) {
        return appUserRoleDao.search(property, searchCriteria, numberOfRows, startIndex);
    }

    public long countAppUserRole() {
        return appUserRoleDao.count();
    }

    @Transactional
    public AppUserRole createAppUserRole(AppUserRole object) {
        return appUserRoleDao.create(object);
    }

    @Transactional
    public AppUserRole updateAppUserRole(AppUserRole object) {
        return appUserRoleDao.update(object);
    }

}

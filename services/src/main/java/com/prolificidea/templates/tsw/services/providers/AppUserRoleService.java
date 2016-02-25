package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.domain.AppUserRole;

import java.util.List;

public interface AppUserRoleService {

    AppUserRole getAppUserRoleByAppUserRoleId(int id);
    List<AppUserRole> getAllAppUserRole();
    List<AppUserRole> getAllAppUserRole(int startIndex, int numberOfRows);
    List<AppUserRole> searchAppUserRole(String property, String searchCriteria);
    List<AppUserRole> searchAppUserRole(String property, String searchCriteria, int startIndex, int numberOfRows);
    long countAppUserRole();
    AppUserRole createAppUserRole(AppUserRole object);
    AppUserRole updateAppUserRole(AppUserRole object);

}
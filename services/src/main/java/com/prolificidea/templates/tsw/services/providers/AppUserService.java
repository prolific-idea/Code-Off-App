package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.domain.AppUser;

import java.util.List;

public interface AppUserService {

    AppUser getAppUserByAppUserId(int id);
    List<AppUser> getAllAppUser();
    List<AppUser> getAllAppUser(int startIndex, int numberOfRows);
    List<AppUser> searchAppUser(String property, String searchCriteria);
    List<AppUser> searchAppUser(String property, String searchCriteria, int startIndex, int numberOfRows);
    long countAppUser();
    AppUser createAppUser(AppUser object);
    AppUser updateAppUser(AppUser object);

}
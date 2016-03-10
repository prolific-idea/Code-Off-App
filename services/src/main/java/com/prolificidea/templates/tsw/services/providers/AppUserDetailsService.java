package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.domain.entities.AppUserDetails;

import java.util.List;

public interface AppUserDetailsService {

    AppUserDetails getAppUserDetailsByAppUserDetailsId(int id);
    List<AppUserDetails> getAllAppUserDetails();
    List<AppUserDetails> getAllAppUserDetails(int startIndex, int numberOfRows);
    List<AppUserDetails> searchAppUserDetails(String property, String searchCriteria);
    List<AppUserDetails> searchAppUserDetails(String property, String searchCriteria, int startIndex, int numberOfRows);
    long countAppUserDetails();
    AppUserDetails createAppUserDetails(AppUserDetails object);
    AppUserDetails updateAppUserDetails(AppUserDetails object);

}
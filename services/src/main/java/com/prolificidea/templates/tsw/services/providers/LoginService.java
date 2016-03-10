package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.domain.entities.AppUser;

/**
 * Created by matthew.jordaan on 2016/03/10.
 */
public interface LoginService {
    AppUser getUserByUsername(String username);
}

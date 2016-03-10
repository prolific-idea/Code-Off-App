package com.prolificidea.templates.tsw.web.security;

import com.prolificidea.templates.tsw.services.security.CustomUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by matthew.jordaan on 2016/03/09.
 */
public class UserAuthentication implements Authentication {
    private final CustomUser user;
    private boolean authenticated = true;

    public UserAuthentication(CustomUser user) {
        this.user = user;
    }

    public String getName() {
        return user.getUsername();
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    public Object getCredentials() {
        return user.getPassword();
    }

    public CustomUser getDetails() {
        return user;
    }

    public Object getPrincipal() {
        return user.getUsername();
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}

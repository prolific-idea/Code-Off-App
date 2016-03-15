package com.prolificidea.templates.tsw.services.security;

import com.prolificidea.templates.tsw.domain.entities.AppUser;
import com.prolificidea.templates.tsw.domain.entities.AppUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by matthew.jordaan on 2016/03/09.
 */
public class CustomUser implements UserDetails{

    private String password;
    private String username;
    private String firstName;
    private String surname;
    private List<GrantedAuthority> authorities;

    public AppUser toDomainUser() {
        final AppUser appUser = new AppUser();
        appUser.setUsername(this.username);
        appUser.setPassword(this.password);
        AppUserDetails appUserDetails = new AppUserDetails();
        appUserDetails.setFirstName(this.firstName);
        appUserDetails.setLastName(this.surname);
        appUser.setAppUserDetails(appUserDetails);
        return appUser;
    }

    public CustomUser() {
    }

    public CustomUser(String username, String password, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public CustomUser(String username, List<GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}

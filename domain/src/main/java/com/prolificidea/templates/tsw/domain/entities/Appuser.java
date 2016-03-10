/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolificidea.templates.tsw.domain.entities;

import com.prolificidea.templates.tsw.domain.AppUserDetails;
import com.prolificidea.templates.tsw.domain.AppUserRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matthew.jordaan
 */
@Entity
@Table(name = "AppUser")
public class AppUser implements java.io.Serializable {

    private int appUserId;
    private AppUserDetails appUserDetails;
    private String username;
    private String password;
    private boolean enabled;
    private List appUserRoles = new ArrayList();

    public AppUser() {
    }

    public AppUser(int appUserId, AppUserDetails appUserDetails,
                   String username, String password, boolean enabled) {
        this.appUserId = appUserId;
        this.appUserDetails = appUserDetails;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public AppUser(int appUserId, AppUserDetails appUserDetails,
                   String username, String password, boolean enabled, List appUserRoles) {
        this.appUserId = appUserId;
        this.appUserDetails = appUserDetails;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.appUserRoles = appUserRoles;
    }

    @Id
    @GeneratedValue
    @Column(name = "AppUserId", unique = true, nullable = false)
    public int getAppUserId() {
        return this.appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AppUserDetails", nullable = false)
    public AppUserDetails getAppUserDetails() {
        return this.appUserDetails;
    }

    public void setAppUserDetails(AppUserDetails appUserDetails) {
        this.appUserDetails = appUserDetails;
    }

    @Column(name = "Username", nullable = false, length = 50)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "Password", nullable = false, length = 100)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "Enabled", nullable = false)
    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appUser")
    public List<AppUserRole> getAppUserRoles() {
        return this.appUserRoles;
    }

    public void setAppUserRoles(List<AppUserRole> appUserRoles) {
        this.appUserRoles = appUserRoles;
    }

}

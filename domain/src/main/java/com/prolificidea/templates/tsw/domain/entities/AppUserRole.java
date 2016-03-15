/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolificidea.templates.tsw.domain.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author matthew.jordaan
 */
@Entity
@Table(name = "appuserrole")
@XmlRootElement
public class AppUserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appUserRoleId")
    private Integer appUserRoleId;
    @JoinColumn(name = "appUserId", referencedColumnName = "appUserId")
    @ManyToOne(optional = false)
    private AppUser appUserId;
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    @ManyToOne(optional = false)
    private Role roleId;

    public AppUserRole() {
    }

    public AppUserRole(Integer appUserRoleId) {
        this.appUserRoleId = appUserRoleId;
    }

    public Integer getAppUserRoleId() {
        return appUserRoleId;
    }

    public void setAppUserRoleId(Integer appUserRoleId) {
        this.appUserRoleId = appUserRoleId;
    }

    public AppUser getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(AppUser appUserId) {
        this.appUserId = appUserId;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appUserRoleId != null ? appUserRoleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppUserRole)) {
            return false;
        }
        AppUserRole other = (AppUserRole) object;
        if ((this.appUserRoleId == null && other.appUserRoleId != null) || (this.appUserRoleId != null && !this.appUserRoleId.equals(other.appUserRoleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.prolificidea.templates.tsw.domain.entities.AppUserRole[ appUserRoleId=" + appUserRoleId + " ]";
    }
    
}

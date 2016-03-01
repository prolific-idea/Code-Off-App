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
@NamedQueries({
    @NamedQuery(name = "Appuserrole.findAll", query = "SELECT a FROM Appuserrole a"),
    @NamedQuery(name = "Appuserrole.findByAppUserRoleId", query = "SELECT a FROM Appuserrole a WHERE a.appUserRoleId = :appUserRoleId")})
public class Appuserrole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appUserRoleId")
    private Integer appUserRoleId;
    @JoinColumn(name = "appUserId", referencedColumnName = "appUserId")
    @ManyToOne(optional = false)
    private Appuser appUserId;
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    @ManyToOne(optional = false)
    private Role roleId;

    public Appuserrole() {
    }

    public Appuserrole(Integer appUserRoleId) {
        this.appUserRoleId = appUserRoleId;
    }

    public Integer getAppUserRoleId() {
        return appUserRoleId;
    }

    public void setAppUserRoleId(Integer appUserRoleId) {
        this.appUserRoleId = appUserRoleId;
    }

    public Appuser getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Appuser appUserId) {
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
        if (!(object instanceof Appuserrole)) {
            return false;
        }
        Appuserrole other = (Appuserrole) object;
        if ((this.appUserRoleId == null && other.appUserRoleId != null) || (this.appUserRoleId != null && !this.appUserRoleId.equals(other.appUserRoleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.prolificidea.templates.tsw.domain.entities.Appuserrole[ appUserRoleId=" + appUserRoleId + " ]";
    }
    
}

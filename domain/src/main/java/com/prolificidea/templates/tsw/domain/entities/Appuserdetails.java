/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolificidea.templates.tsw.domain.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author matthew.jordaan
 */
@Entity
@Table(name = "appuserdetails")
@XmlRootElement
public class Appuserdetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appUserDetailsId")
    private Integer appUserDetailsId;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "emailAddress")
    private String emailAddress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appUserDetailsId")
    private List<AppUser> appuserList;

    public Appuserdetails() {
    }

    public Appuserdetails(Integer appUserDetailsId) {
        this.appUserDetailsId = appUserDetailsId;
    }

    public Integer getAppUserDetailsId() {
        return appUserDetailsId;
    }

    public void setAppUserDetailsId(Integer appUserDetailsId) {
        this.appUserDetailsId = appUserDetailsId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @XmlTransient
    public List<AppUser> getAppuserList() {
        return appuserList;
    }

    public void setAppuserList(List<AppUser> appuserList) {
        this.appuserList = appuserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appUserDetailsId != null ? appUserDetailsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appuserdetails)) {
            return false;
        }
        Appuserdetails other = (Appuserdetails) object;
        if ((this.appUserDetailsId == null && other.appUserDetailsId != null) || (this.appUserDetailsId != null && !this.appUserDetailsId.equals(other.appUserDetailsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.prolificidea.templates.tsw.domain.entities.Appuserdetails[ appUserDetailsId=" + appUserDetailsId + " ]";
    }
    
}

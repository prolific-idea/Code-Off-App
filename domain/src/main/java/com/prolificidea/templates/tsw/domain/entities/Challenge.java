/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolificidea.templates.tsw.domain.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matthew.jordaan
 */
@Entity
@Table(name = "challenge")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Challenge.findAll", query = "SELECT c FROM Challenge c"),
    @NamedQuery(name = "Challenge.findByChallengeId", query = "SELECT c FROM Challenge c WHERE c.challengeId = :challengeId"),
    @NamedQuery(name = "Challenge.findByUrl", query = "SELECT c FROM Challenge c WHERE c.url = :url"),
    @NamedQuery(name = "Challenge.findBySolutionFilePath", query = "SELECT c FROM Challenge c WHERE c.solutionFilePath = :solutionFilePath"),
    @NamedQuery(name = "Challenge.findByStartDate", query = "SELECT c FROM Challenge c WHERE c.startDate = :startDate"),
    @NamedQuery(name = "Challenge.findByEndDate", query = "SELECT c FROM Challenge c WHERE c.endDate = :endDate")})
public class Challenge implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "challengeId")
    private Integer challengeId;
    @Basic(optional = false)
    @Lob
    @Column(name = "solution")
    private byte[] solution;
    @Basic(optional = false)
    @Column(name = "url")
    private String url;
    @Column(name = "solutionFilePath")
    private String solutionFilePath;
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "challengeId")
    private List<Entry> entryList;

    public Challenge() {
    }

    public Challenge(Integer challengeId) {
        this.challengeId = challengeId;
    }

    public Challenge(Integer challengeId, byte[] solution, String url, Date endDate) {
        this.challengeId = challengeId;
        this.solution = solution;
        this.url = url;
        this.endDate = endDate;
    }

    public Integer getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Integer challengeId) {
        this.challengeId = challengeId;
    }

    public byte[] getSolution() {
        return solution;
    }

    public void setSolution(byte[] solution) {
        this.solution = solution;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSolutionFilePath() {
        return solutionFilePath;
    }

    public void setSolutionFilePath(String solutionFilePath) {
        this.solutionFilePath = solutionFilePath;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @XmlTransient
    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (challengeId != null ? challengeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Challenge)) {
            return false;
        }
        Challenge other = (Challenge) object;
        if ((this.challengeId == null && other.challengeId != null) || (this.challengeId != null && !this.challengeId.equals(other.challengeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.prolificidea.templates.tsw.domain.entities.Challenge[ challengeId=" + challengeId + " ]";
    }
    
}

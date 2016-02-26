/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolificidea.templates.tsw.domain.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matthew.jordaan
 */
@Entity
@Table(name = "entry")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entry.findAll", query = "SELECT e FROM Entry e"),
    @NamedQuery(name = "Entry.findByEntryId", query = "SELECT e FROM Entry e WHERE e.entryId = :entryId"),
    @NamedQuery(name = "Entry.findByDate", query = "SELECT e FROM Entry e WHERE e.date = :date"),
    @NamedQuery(name = "Entry.findByUrl", query = "SELECT e FROM Entry e WHERE e.url = :url"),
    @NamedQuery(name = "Entry.findByResult", query = "SELECT e FROM Entry e WHERE e.result = :result")})
public class Entry implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "entryId")
    private Integer entryId;
    @Lob
    @Column(name = "solution")
    private byte[] solution;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "url")
    private String url;
    @Column(name = "result")
    private Integer result;
    @JoinColumn(name = "challengeId", referencedColumnName = "challengeId")
    @ManyToOne(optional = false)
    private Challenge challengeId;
    @JoinColumn(name = "personId", referencedColumnName = "personId")
    @ManyToOne(optional = false)
    private Person personId;
    @JoinColumn(name = "techId", referencedColumnName = "techId")
    @ManyToOne(optional = false)
    private Technology techId;

    public Entry() {
    }

    public Entry(Integer entryId) {
        this.entryId = entryId;
    }

    public Entry(Integer entryId, Date date) {
        this.entryId = entryId;
        this.date = date;
    }

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public byte[] getSolution() {
        return solution;
    }

    public void setSolution(byte[] solution) {
        this.solution = solution;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Challenge getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Challenge challengeId) {
        this.challengeId = challengeId;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public Technology getTechId() {
        return techId;
    }

    public void setTechId(Technology techId) {
        this.techId = techId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entryId != null ? entryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entry)) {
            return false;
        }
        Entry other = (Entry) object;
        if ((this.entryId == null && other.entryId != null) || (this.entryId != null && !this.entryId.equals(other.entryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.prolificidea.templates.tsw.domain.entities.Entry[ entryId=" + entryId + " ]";
    }
    
}

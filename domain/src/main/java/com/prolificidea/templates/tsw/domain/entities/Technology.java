package com.prolificidea.templates.tsw.domain.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matthew.jordaan
 */
@Entity
@Table(name = "technology")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Technology.findAll", query = "SELECT t FROM Technology t"),
        @NamedQuery(name = "Technology.findByTechId", query = "SELECT t FROM Technology t WHERE t.techId = :techId"),
        @NamedQuery(name = "Technology.findByDescription", query = "SELECT t FROM Technology t WHERE t.description = :description")})
public class Technology implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "techId", nullable = false, unique = true)
    private Integer techId;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "techId")
    private List<Entry> entryList;

    public Technology() {
    }

    public Technology(Integer techId) {
        this.techId = techId;
    }

    public Integer getTechId() {
        return techId;
    }

    public void setTechId(Integer techId) {
        this.techId = techId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (techId != null ? techId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Technology)) {
            return false;
        }
        Technology other = (Technology) object;
        if ((this.techId == null && other.techId != null) || (this.techId != null && !this.techId.equals(other.techId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.prolificidea.templates.tsw.domain.entities.Technology[ techId=" + techId + " ]";
    }

}
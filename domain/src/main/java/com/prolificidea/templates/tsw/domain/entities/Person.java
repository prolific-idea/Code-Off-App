/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prolificidea.templates.tsw.domain.entities;

import org.hibernate.annotations.Formula;

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
@Table(name = "person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByPersonId", query = "SELECT p FROM Person p WHERE p.personId = :personId"),
    @NamedQuery(name = "Person.findByUsername", query = "SELECT p FROM Person p WHERE p.username = :username"),
    @NamedQuery(name = "Person.findByFirstName", query = "SELECT p FROM Person p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Person.findByLastName", query = "SELECT p FROM Person p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Person.findByScore", query = "SELECT p FROM Person p WHERE p.score = :score"),
    @NamedQuery(name = "Person.findByUrl", query = "SELECT p FROM Person p WHERE p.url = :url")})
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "personId", nullable = false, unique = true)
    private Integer personId;
    @Basic(optional = false)
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "score", nullable = false)
    private int score;
    @Column(name = "url")
    private String url;
    @Column(name = "variance")
    private int variance;
    @Formula(value = "score+variance")
    private long actualScore;

    //    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId")
//    private List<Entry> entryList;

    public Person() {
    }

    public Person(Integer personId) {
        this.personId = personId;
    }

    public Person(Integer personId, String username, int score) {
        this.personId = personId;
        this.username = username;
        this.score = score;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getUsername() {
        return username;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVariance() {
        return variance;
    }

    public long getActualScore() {
        return actualScore;
    }

    public void setActualScore() {
        this.actualScore = score+variance;
    }

    public void setVariance(int variance) {
        this.variance = variance;
    }

//    @XmlTransient
//    public List<Entry> getEntryList() {
//        return entryList;
//    }
//
//    public void setEntryList(List<Entry> entryList) {
//        this.entryList = entryList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.prolificidea.templates.tsw.domain.entities.Person[ personId=" + personId + " ]";
    }

}

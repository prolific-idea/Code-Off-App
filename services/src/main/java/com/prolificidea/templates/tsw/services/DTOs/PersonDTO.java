package com.prolificidea.templates.tsw.services.DTOs;

import com.prolificidea.templates.tsw.domain.entities.Person;

import java.io.Serializable;

/**
 * Created by matthew.jordaan on 2016/03/02.
 */
public class PersonDTO implements Serializable {

    private Integer personId;
    private String username;
    private String firstName;
    private String lastName;
    private int score;
    private String url;
    private int variance;

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    private String repoUrl;

    public PersonDTO() {
    }

    public PersonDTO(Person p) {
        this.variance = p.getVariance();
        this.personId = p.getPersonId();
        this.username = p.getUsername();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.score = p.getScore();
        this.url = p.getUrl();

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

    public void setVariance(int variance) {
        this.variance = variance;
    }
}

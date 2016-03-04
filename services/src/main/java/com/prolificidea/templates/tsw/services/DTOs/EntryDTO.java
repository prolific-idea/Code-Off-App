package com.prolificidea.templates.tsw.services.DTOs;

import com.prolificidea.templates.tsw.domain.entities.Entry;

import java.util.Date;

/**
 * Created by matthew.jordaan on 2016/03/02.
 */
public class EntryDTO {

    private Integer entryId;
    private byte[] solution;
    private Date date;
    private String url;
    private Integer result;
    private int challengeId;
    private int personId;
    private int techId;
    private String fullName;
    private String branch;

    public EntryDTO() {
    }

    public EntryDTO(Entry e) {
        this.entryId = e.getEntryId();
        this.solution = e.getSolution();
        this.date = e.getDate();
        this.url = e.getUrl();
        this.result = e.getResult();
        this.challengeId = e.getChallengeId().getChallengeId();
        this.personId = e.getPersonId().getPersonId();
        this.techId = e.getTechId().getTechId();
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

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getTechId() {
        return techId;
    }

    public void setTechId(int techId) {
        this.techId = techId;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}

package com.prolificidea.templates.tsw.services.DTOs;

import com.prolificidea.templates.tsw.domain.entities.Challenge;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by matthew.jordaan on 2016/03/02.
 */
public class ChallengeDTO implements Serializable {

    private Integer challengeId;
    private byte[] solution;
    private String url;
    private String solutionFilePath;
    private Date startDate;
    private Date endDate;
    private Integer numberOfLinesToCompare;

    public ChallengeDTO() {
    }

    public ChallengeDTO(Challenge challenge) {
        this.challengeId = challenge.getChallengeId();
        this.solution = challenge.getSolution();
        this.url = challenge.getUrl();
        this.solutionFilePath = challenge.getSolutionFilePath();
        this.startDate = challenge.getStartDate();
        this.endDate = challenge.getEndDate();
        this.numberOfLinesToCompare = challenge.getNumberOfLinesToCompare();
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

    public Integer getNumberOfLinesToCompare() {
        return numberOfLinesToCompare;
    }

    public void setNumberOfLinesToCompare(Integer numberOfLinesToCompare) {
        this.numberOfLinesToCompare = numberOfLinesToCompare;
    }
}

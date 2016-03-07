package com.prolificidea.templates.tsw.services.DTOs;

import com.prolificidea.templates.tsw.domain.entities.Leaderboard;

import java.util.List;

/**
 * Created by matthew.jordaan on 2016/03/03.
 */
public class LeaderboardDTO {

    private int leaderboardId;
    private int personId;
    private int noCodeOffs;
    private int points;
    private List<TechnologyDTO> techs;

    public LeaderboardDTO() {
    }

    public LeaderboardDTO(Leaderboard leaderboard) {
        this.leaderboardId = leaderboard.getLeaderboardId();
        this.personId = leaderboard.getPerson().getPersonId();
        this.noCodeOffs = leaderboard.getNoCodeOffs();
        this.points = leaderboard.getPoints();
        this.techs = null;
    }



    public LeaderboardDTO(PersonDTO person, int noCodeOffs, List<TechnologyDTO> techs) {
        this.personId = person.getPersonId();
        this.points = person.getScore();
        this.noCodeOffs = noCodeOffs;
        this.techs = techs;
    }

    public int getLeaderboardId() {
        return leaderboardId;
    }

    public void setLeaderboardId(int leaderboardId) {
        this.leaderboardId = leaderboardId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getNoCodeOffs() {
        return noCodeOffs;
    }

    public void setNoCodeOffs(int noCodeOffs) {
        this.noCodeOffs = noCodeOffs;
    }

    public int getPoints() {
        return points;
    }

    public List<TechnologyDTO> getTechs() {
        return techs;
    }

    public void setTechs(List<TechnologyDTO> techs) {
        this.techs = techs;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

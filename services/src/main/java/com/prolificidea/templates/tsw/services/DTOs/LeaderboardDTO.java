package com.prolificidea.templates.tsw.services.DTOs;

import com.prolificidea.templates.tsw.domain.entities.Leaderboard;

import java.util.List;

/**
 * Created by matthew.jordaan on 2016/03/03.
 */
public class LeaderboardDTO {

    private int leaderboardId;
    private PersonDTO personDTO;
    private int noCodeOffs;
    private int points;
    private List<TechnologyDTO> techs;

    public LeaderboardDTO() {
    }

    public LeaderboardDTO(Leaderboard leaderboard) {
        this.noCodeOffs = leaderboard.getNoCodeOffs();
        this.personDTO = new PersonDTO(leaderboard.getPerson());
        this.points = leaderboard.getPoints();
        this.leaderboardId = leaderboard.getLeaderboardId();
    }


    public LeaderboardDTO(PersonDTO person, int noCodeOffs, List<TechnologyDTO> techs) {
        this.personDTO = person;
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

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public void setPersonDTO(PersonDTO personDTO) {
        this.personDTO = personDTO;
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

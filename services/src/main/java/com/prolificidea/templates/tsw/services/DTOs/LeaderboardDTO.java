package com.prolificidea.templates.tsw.services.DTOs;

import com.prolificidea.templates.tsw.domain.entities.Leaderboard;

/**
 * Created by matthew.jordaan on 2016/03/03.
 */
public class LeaderboardDTO {

    private int leaderboardId;
    private int personId;
    private int noCodeOffs;
    private int points;

    public LeaderboardDTO() {
    }

    public LeaderboardDTO(Leaderboard leaderboard) {
        this.leaderboardId = leaderboard.getLeaderboardId();
        this.personId = leaderboard.getPerson().getPersonId();
        this.noCodeOffs = leaderboard.getNoCodeOffs();
        this.points = leaderboard.getPoints();
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

    public void setPoints(int points) {
        this.points = points;
    }
}

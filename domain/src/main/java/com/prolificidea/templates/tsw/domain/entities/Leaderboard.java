package com.prolificidea.templates.tsw.domain.entities;

import javax.persistence.*;

/**
 * Created by matthew.jordaan on 2016/03/03.
 */
@Entity
@Table(name ="leaderboard")
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "leaderboardId")
    private int leaderboardId;
    @JoinColumn(name = "personId", referencedColumnName = "personId", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Person person;
    @Column(name = "noCodeOffs")
    private int noCodeOffs;
    @Column(name = "points")
    private int points;

    public Leaderboard() {
    }

    public Leaderboard(Person person, int noCodeOffs, int points) {
        this.person = person;
        this.noCodeOffs = noCodeOffs;
        this.points = points;
    }

    public int getLeaderboardId() {
        return leaderboardId;
    }

    public void setLeaderboardId(int leaderboardId) {
        this.leaderboardId = leaderboardId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

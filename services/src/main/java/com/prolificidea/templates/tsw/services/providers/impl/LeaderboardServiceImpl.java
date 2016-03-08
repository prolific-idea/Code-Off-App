package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Leaderboard;
import com.prolificidea.templates.tsw.persistence.LeaderboardDao;
import com.prolificidea.templates.tsw.persistence.PersonDao;
import com.prolificidea.templates.tsw.services.DTOs.LeaderboardDTO;
import com.prolificidea.templates.tsw.services.providers.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthew.jordaan on 2016/03/03.
 */
@Service
public class LeaderboardServiceImpl implements LeaderboardService {

    @Autowired
    LeaderboardDao leaderboardDao;

    @Autowired
    PersonDao personDao;

    public LeaderboardDTO findLeaderboard(Object id) {
        return new LeaderboardDTO(leaderboardDao.find(id));
    }

    public List<LeaderboardDTO> findAllLeaderboards() {
        return convertDomainListToDtoList(leaderboardDao.findAll());
    }

    public List<LeaderboardDTO> findAllLeaderboards(int pageSize, int pageNumber) {
        return  convertDomainListToDtoList(leaderboardDao.findAll(pageSize, pageNumber));
    }

    public List<LeaderboardDTO> searchLeaderboards(String property, String criteria) {
        return convertDomainListToDtoList(leaderboardDao.search(property, criteria));
    }

    public List<LeaderboardDTO> searchLeaderboards(String property, String criteria, int pageSize, int pageNumber) {
        return convertDomainListToDtoList(leaderboardDao.search(property, criteria, pageSize, pageNumber));
    }

    public long countLeaderboards() {
        return leaderboardDao.count();
    }

    @Transactional
    public void deleteLeaderboard(Object id) {
        leaderboardDao.delete(id);
    }

    @Transactional
    public LeaderboardDTO createLeaderboard(LeaderboardDTO t) {
        Leaderboard l = new Leaderboard();
        l.setNoCodeOffs(t.getNoCodeOffs());
        l.setPerson(personDao.find(t.getPersonDTO()));
        l.setPoints(t.getPoints());

        return new LeaderboardDTO(leaderboardDao.create(l));
    }

    @Transactional
    public LeaderboardDTO updateLeaderboard(LeaderboardDTO t) {
        Leaderboard l = leaderboardDao.find(t.getLeaderboardId());
        l.setNoCodeOffs(t.getNoCodeOffs());
        l.setPerson(personDao.find(t.getPersonDTO()));
        l.setPoints(t.getPoints());
        return new LeaderboardDTO(leaderboardDao.update(l));
    }









    private List<LeaderboardDTO> convertDomainListToDtoList(List<Leaderboard> Leaderboards) {
        List<LeaderboardDTO> LeaderboardDTOs = new ArrayList<LeaderboardDTO>();
        for (Leaderboard l : Leaderboards)
        {
            LeaderboardDTOs.add(new LeaderboardDTO(l));

        }
        return LeaderboardDTOs;
    }
}

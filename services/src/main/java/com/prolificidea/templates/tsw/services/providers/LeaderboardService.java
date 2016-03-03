package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.services.DTOs.LeaderboardDTO;

import java.util.List;

/**
 * Created by matthew.jordaan on 2016/03/03.
 */
public interface LeaderboardService {

    LeaderboardDTO findLeaderboard(Object id);
    List<LeaderboardDTO> findAllLeaderboards();
    List<LeaderboardDTO> findAllLeaderboards(int pageSize, int pageNumber);
    List<LeaderboardDTO> searchLeaderboards(String property, String criteria);
    List<LeaderboardDTO> searchLeaderboards(String property, String criteria, int pageSize, int pageNumber);
    long countLeaderboards();
    void deleteLeaderboard(Object id);
    LeaderboardDTO createLeaderboard(LeaderboardDTO t);
    LeaderboardDTO updateLeaderboard(LeaderboardDTO t);
}

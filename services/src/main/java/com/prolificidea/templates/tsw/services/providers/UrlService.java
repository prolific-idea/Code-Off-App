package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;

public interface UrlService {

    String getContent(String downloadUrl);

    boolean compareSolution(String solution, ChallengeDTO challenge);
}

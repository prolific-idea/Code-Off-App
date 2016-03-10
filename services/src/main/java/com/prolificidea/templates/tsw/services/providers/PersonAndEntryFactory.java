package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;
import org.json.JSONException;

public interface PersonAndEntryFactory {

    String markSolutionsOfUserIfTheyExsistForAChallenge(ChallengeDTO pollingChallenge) throws JSONException ;
}

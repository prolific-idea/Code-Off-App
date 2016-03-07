package com.prolificidea.templates.tsw.services.providers;

import org.json.JSONException;

public interface PersonAndEntryFactory {

    String markSolutionsOfUserIfTheyExsistForAChallenge(int challengeId) throws JSONException ;
}

package com.prolificidea.templates.tsw.services.providers.impl;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class PollingReport {

    private JSONObject report;


    public JSONObject getReport() {
        return report;
    }

    public void intalize(int MINUTES_BETWEEN_POLLS) {
        report = new JSONObject();
        try {
            report = new JSONObject();
            report.put("last_polled", new Date()).toString();
            report.put("polling_status","Currently Polling");
            LocalDateTime time = LocalDateTime.now();
            time = time.plusMinutes(MINUTES_BETWEEN_POLLS);
            report.put("next_poll",time.toLocalTime().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void endReport() {
        try {
            report.put("polling_status","Finished Polling");
            report.put("finished_polled", new Date()).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putArray(String challenges_polled, JSONArray challengesArray) throws JSONException {
        report.put("challenges_polled",challengesArray);
    }
}

package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.services.DTOs.EntryDTO;

/**
 * Created by matthew.jordaan on 2016/03/01.
 */
public interface ScoreService {

    void addScore(EntryDTO entry);

}

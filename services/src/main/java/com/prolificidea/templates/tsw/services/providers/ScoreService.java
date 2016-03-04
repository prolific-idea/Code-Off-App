package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.services.DTOs.EntryDTO;
import com.prolificidea.templates.tsw.services.DTOs.PersonDTO;

/**
 * Created by matthew.jordaan on 2016/03/01.
 */
public interface ScoreService {


    void addScore(EntryDTO entry);
    void recalculateScores(PersonDTO personDTO);

}

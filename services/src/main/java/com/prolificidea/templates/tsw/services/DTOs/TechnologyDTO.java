package com.prolificidea.templates.tsw.services.DTOs;

import com.prolificidea.templates.tsw.domain.entities.Technology;

import java.io.Serializable;

/**
 * Created by matthew.jordaan on 2016/03/02.
 */
public class TechnologyDTO implements Serializable {

    private Integer techId;
    private String description;

    public TechnologyDTO() {
    }

    public TechnologyDTO(Technology t){
        techId = t.getTechId();
        description = t.getDescription();
    }

    public Integer getTechId() {
        return techId;
    }

    public void setTechId(Integer techId) {
        this.techId = techId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

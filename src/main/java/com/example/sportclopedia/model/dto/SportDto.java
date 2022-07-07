package com.example.sportclopedia.model.dto;

import com.example.sportclopedia.model.entity.Training;

import java.util.List;

public class SportDto {

    private Long id;
    private String name;
    private String description;
    private String trainingName;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTraining() {
        return trainingName;
    }

    public void setTraining(String training) {
        this.trainingName = training;
    }

}

package com.example.sportclopedia.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sports")
public class Sport extends BaseEntity{

    private String name;
    private String description;
    private List<Training> trainings;



    @Column(nullable = false)
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

    @OneToMany(mappedBy = "sport")
    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }
}

package com.example.sportclopedia.model.entity;

import com.example.sportclopedia.model.enums.IntensityLevelEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "trainings")
public class Training extends BaseEntity{

    private String name;
    private Integer duration;
    private IntensityLevelEnum intensity;
    private LocalDateTime startedOn;
    private String description;
    private Coach coach;
    private Sport sport;
    private Hall hall;
    private List<User> users;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer daysPerWeek) {
        this.duration = daysPerWeek;
    }

    public IntensityLevelEnum getIntensity() {
        return intensity;
    }

    public void setIntensity(IntensityLevelEnum intensity) {
        this.intensity = intensity;
    }

    @Column(nullable = false)
    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    @ManyToOne
    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
    @ManyToOne
    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    @ManyToMany(mappedBy = "trainings",fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

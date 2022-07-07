package com.example.sportclopedia.model.dto;

import com.example.sportclopedia.model.enums.IntensityLevelEnum;


import java.time.LocalDateTime;

public class TrainingDto {

    private String name;
    private Integer duration;
    private IntensityLevelEnum intensity;
    private LocalDateTime startedOn;
    private String coach;
    private String sport;
    private String hall;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public IntensityLevelEnum getIntensity() {
        return intensity;
    }

    public void setIntensity(IntensityLevelEnum intensity) {
        this.intensity = intensity;
    }

    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }
}

package com.example.sportclopedia.model.dto;

import com.example.sportclopedia.model.enums.IntensityLevelEnum;


import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class TrainingDto {

    private Long id;
    private String name;
    private Integer duration;
    private IntensityLevelEnum intensity;
    private LocalDateTime startedOn;
    private String description;
    private String coachFullName;
    private String sportName;
    private String hallName;
    private Long sportId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoachFullName() {
        return coachFullName;
    }

    public void setCoachFullName(String coachFullName) {
        this.coachFullName = coachFullName;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }
}

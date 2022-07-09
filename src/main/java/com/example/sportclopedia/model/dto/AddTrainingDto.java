package com.example.sportclopedia.model.dto;

import com.example.sportclopedia.model.enums.IntensityLevelEnum;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class AddTrainingDto {

    private String name;
    private Integer duration;
    private IntensityLevelEnum intensity;
    private LocalDateTime startedOn;
    private String coach;
    private String sport;
    private String hall;

    @Size(min = 3,max = 50)
    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Positive
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @NotBlank
    public IntensityLevelEnum getIntensity() {
        return intensity;
    }

    public void setIntensity(IntensityLevelEnum intensity) {
        this.intensity = intensity;
    }

    @PastOrPresent
    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
    }

    @NotBlank
    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    @NotBlank
    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    @NotBlank
    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }
}

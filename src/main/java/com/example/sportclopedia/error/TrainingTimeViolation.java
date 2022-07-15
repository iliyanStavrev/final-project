package com.example.sportclopedia.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class TrainingTimeViolation extends RuntimeException{

    private String name;
    private LocalDateTime date;

    public TrainingTimeViolation(String name, LocalDateTime date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
        return date;
    }
}

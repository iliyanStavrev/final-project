package com.example.sportclopedia.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ConstraintViolationException extends RuntimeException{

    private Long id;
    private String fullName;

    public ConstraintViolationException(Long id, String fullName){
        this.id = id;
        this.fullName = fullName;
    }


    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}

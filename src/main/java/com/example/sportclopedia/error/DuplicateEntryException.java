package com.example.sportclopedia.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DuplicateEntryException extends RuntimeException {

   private String username;

    public DuplicateEntryException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}

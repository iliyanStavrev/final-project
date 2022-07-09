package com.example.sportclopedia.model.dto;

import java.time.LocalDate;

public class CoachDto {

    private String fullName;
    private Integer age;
    private LocalDate coachSince;
    private String phoneNumber;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getCoachSince() {
        return coachSince;
    }

    public void setCoachSince(LocalDate coachFrom) {
        this.coachSince = coachFrom;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

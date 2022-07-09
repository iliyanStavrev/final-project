package com.example.sportclopedia.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity(name = "coaches")
public class Coach extends BaseEntity{

    private String fullName;
    private Integer age;
    private LocalDate coachSince;
    private String phoneNumber;

    @Column(nullable = false)
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

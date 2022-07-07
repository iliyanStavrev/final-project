package com.example.sportclopedia.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity(name = "coaches")
public class Coach extends BaseEntity{

    private String fullName;
    private Integer age;
    private LocalDate coachFrom;
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

    public LocalDate getCoachFrom() {
        return coachFrom;
    }

    public void setCoachFrom(LocalDate coachFrom) {
        this.coachFrom = coachFrom;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

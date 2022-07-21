package com.example.sportclopedia.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "coaches")
public class Coach extends BaseEntity{

    private String fullName;
    private Integer age;
    private LocalDate coachSince;
    private String phoneNumber;
    private List<Training> trainings;

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

    @OneToMany(mappedBy = "coach")
    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }
}

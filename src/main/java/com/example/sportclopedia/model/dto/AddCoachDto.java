package com.example.sportclopedia.model.dto;

import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AddCoachDto {

    private String fullName;
    private Integer age;
    private LocalDate coachSince;
    private String phoneNumber;

    @Size(min = 2,max = 30)
    @NotBlank
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Positive
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    public LocalDate getCoachSince() {
        return coachSince;
    }

    public void setCoachSince(LocalDate coachSince) {
        this.coachSince = coachSince;
    }

    @Size(min = 10,max = 15)
    @NotBlank
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

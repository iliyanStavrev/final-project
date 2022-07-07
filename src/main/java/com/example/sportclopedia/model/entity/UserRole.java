package com.example.sportclopedia.model.entity;

import com.example.sportclopedia.model.enums.UserRoleEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class UserRole extends BaseEntity{

    private UserRoleEnum userRole;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
    }
}

package com.example.sportclopedia.repository;

import com.example.sportclopedia.model.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    Coach findByFullName(String coach);

    Coach findByFullNameAndPhoneNumber(String fullName, String phoneNumber);

}


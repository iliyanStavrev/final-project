package com.example.sportclopedia.repository;

import com.example.sportclopedia.model.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {
    Sport findByName(String name);
}

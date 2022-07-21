package com.example.sportclopedia.repository;

import com.example.sportclopedia.model.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<Hall,Long> {

    Hall findByName(String name);

    Hall findByNameAndAddress(String name, String address);

}

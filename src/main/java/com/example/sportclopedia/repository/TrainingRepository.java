package com.example.sportclopedia.repository;

import com.example.sportclopedia.model.dto.TrainingDto;
import com.example.sportclopedia.model.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

   List<Training> findBySport_Name(String sportName);

    Training findByNameAndSport_NameAndStartedOn(String name, String sport,
                                                 LocalDateTime startedOn);

}

package com.example.sportclopedia.service.impl;

import com.example.sportclopedia.model.entity.Coach;
import com.example.sportclopedia.repository.CoachRepository;
import com.example.sportclopedia.service.CoachService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CoachServiceImpl implements CoachService {
    private final CoachRepository coachRepository;

    public CoachServiceImpl(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @Override
    public void initCoach() {
        if (coachRepository.count() > 0){
            return;
        }
        Coach coach = new Coach();
        coach.setFullName("Iliyan Stavrev");
        coach.setCoachFrom(LocalDate
                .parse("2004-07-07",DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        coach.setAge(37);
        coachRepository.save(coach);
    }

    @Override
    public Coach findById(Long id) {
        return coachRepository.findById(id)
                .orElse(null);
    }
}

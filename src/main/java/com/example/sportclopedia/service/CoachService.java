package com.example.sportclopedia.service;

import com.example.sportclopedia.model.dto.AddCoachDto;
import com.example.sportclopedia.model.dto.CoachDto;
import com.example.sportclopedia.model.entity.Coach;

import java.util.List;

public interface CoachService {
    void initCoach();

    Coach findById(Long id);

    List<CoachDto> getAllCoaches();

    Coach findByName(String coach);

    List<String> getAllCoachesNames();

    void addCoach(AddCoachDto addCoachDto);

    void deleteCoach(Long id);

    boolean isCoachAdded(AddCoachDto addCoachDto);
}

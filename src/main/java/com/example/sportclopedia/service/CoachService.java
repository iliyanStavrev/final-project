package com.example.sportclopedia.service;

import com.example.sportclopedia.model.entity.Coach;

public interface CoachService {
    void initCoach();

    Coach findById(Long id);
}

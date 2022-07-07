package com.example.sportclopedia.service;

import com.example.sportclopedia.model.dto.SportDto;
import com.example.sportclopedia.model.entity.Sport;

import java.util.List;

public interface SportService {

    void initSport();
    SportDto findById(Long id);

    List<SportDto> findAll();

    Sport findByName(String basketball);
}

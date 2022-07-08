package com.example.sportclopedia.service;


import com.example.sportclopedia.model.dto.HallDto;
import com.example.sportclopedia.model.entity.Hall;

import java.util.List;

public interface HallService {

    void initHall();

    Hall findByName(String sila);

    List<HallDto> getAllHalls();
}

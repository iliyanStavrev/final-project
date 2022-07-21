package com.example.sportclopedia.service;


import com.example.sportclopedia.model.dto.AddHallDto;
import com.example.sportclopedia.model.dto.HallDto;
import com.example.sportclopedia.model.entity.Hall;

import java.util.List;

public interface HallService {

    void initHall();

    Hall findByName(String name);

    List<HallDto> getAllHalls();

    void addHall(AddHallDto addHallDto);

    void deleteHall(Long id);

    boolean isHallAdded(AddHallDto addHallDto);
}

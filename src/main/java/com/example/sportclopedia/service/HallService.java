package com.example.sportclopedia.service;


import com.example.sportclopedia.model.entity.Hall;

public interface HallService {

    void initHall();

    Hall findByName(String sila);
}

package com.example.sportclopedia.service.impl;

import com.example.sportclopedia.model.entity.Hall;
import com.example.sportclopedia.repository.HallRepository;
import com.example.sportclopedia.service.HallService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;

    public HallServiceImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public void initHall() {
        if (hallRepository.count() > 0){
            return;
        }
        Hall hall = new Hall();
        hall.setName("SILA");
        hall.setCapacity(500);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        hall.setAvailableOn(LocalDateTime.parse("2022-08-13 15:33",formatter));
        hall.setAddress("Nikola Vapcarov 31");
        hallRepository.save(hall);
    }

    @Override
    public Hall findByName(String name) {
        return hallRepository
                .findByName(name);
    }
}

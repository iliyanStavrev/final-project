package com.example.sportclopedia.service.impl;

import com.example.sportclopedia.model.dto.HallDto;
import com.example.sportclopedia.model.entity.Hall;
import com.example.sportclopedia.repository.HallRepository;
import com.example.sportclopedia.service.HallService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final ModelMapper modelMapper;

    public HallServiceImpl(HallRepository hallRepository, ModelMapper modelMapper) {
        this.hallRepository = hallRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initHall() {
        if (hallRepository.count() > 1){
            return;
        }
        Hall hall = new Hall();
        hall.setName("SILA");
        hall.setCapacity(500);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        hall.setAvailableOn(LocalDateTime.parse("2022-08-13 15:33",formatter));
        hall.setAddress("Nikola Vapcarov 31");

        Hall hall1 = new Hall();
        hall1.setName("Pulse");
        hall1.setCapacity(250);
        hall1.setAvailableOn(LocalDateTime.parse("2022-08-11 17:33",formatter));
        hall1.setAddress("4000 Plovdiv bul.Hristo Botev 33");

        hallRepository.save(hall);
        hallRepository.save(hall1);

    }

    @Override
    public Hall findByName(String name) {
        return hallRepository
                .findByName(name);
    }

    @Override
    public List<HallDto> getAllHalls() {
        return hallRepository
                .findAll()
                .stream()
                .map(hall -> modelMapper
                        .map(hall, HallDto.class))
                .toList();
    }
}

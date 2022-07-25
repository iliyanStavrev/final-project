package com.example.sportclopedia.service.impl;



import com.example.sportclopedia.model.dto.SportDto;
import com.example.sportclopedia.model.entity.Sport;
import com.example.sportclopedia.model.entity.Training;
import com.example.sportclopedia.repository.SportRepository;
import com.example.sportclopedia.service.SportService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SportServiceImpl implements SportService {

    private final SportRepository sportRepository;
    private final ModelMapper modelMapper;

    public SportServiceImpl(SportRepository sportRepository, ModelMapper modelMapper) {
        this.sportRepository = sportRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initSport() {
        if (sportRepository.count() > 0){
            return;
        }
        List<Sport> sports = new ArrayList<>();

        Sport sport = new Sport();
        sport.setName("Basketball");
        sport.setDescription("That is beautiful sport for clever people!It is very intensive sport " +
                "and it is prefer for people which want to lose fat quickly! ");

        Sport sport1 = new Sport();
        sport1.setName("Fitness");
        sport1.setDescription("Fitness is the most useful sport in the World!");

        Sport sport2 = new Sport();
        sport2.setName("Tennis");
        sport2.setDescription("Tennis is good for developing whole body!");

        Sport sport3 = new Sport();
        sport3.setName("Volleyball");
        sport3.setDescription("volleyball, Game played by two teams of six players each, in which an inflated ball is volleyed over a high net. Each team tries to make the ball touch the court within the opposing side's playing areas before it can be returned. A team is allowed to touch the ball three times before returning it.");

        sports.add(sport);
        sports.add(sport1);
        sports.add(sport2);
        sports.add(sport3);

        sportRepository.saveAll(sports);

    }

    @Override
    public SportDto findById(Long id) {
        return sportRepository
                .findById(id)
                .map(sport -> {
                    SportDto sportDto = modelMapper
                            .map(sport,SportDto.class);
                    sportDto.setTraining(sport.getTrainings()
                            .stream()
                            .filter(t->t.getSport().getName().equals(sport.getName()))
                            .map(Training::getName)
                            .toString());
                    return sportDto;
                })
                .orElse(null);
    }

    @Override
    public List<SportDto> findAll() {
        return sportRepository.findAll()
                .stream()
                .map(sport -> modelMapper
                        .map(sport, SportDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Sport findByName(String name) {
        return sportRepository
                .findByName(name);
    }

    @Override
    public List<String> getAllSportsNames() {
        return sportRepository
                .findAll()
                .stream()
                .map(Sport::getName)
                .toList();
    }
}

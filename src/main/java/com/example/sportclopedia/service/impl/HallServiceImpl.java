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
        hall.setDescription("Multifunctional hall for sports (tennis, basketball, volleyball, handball, badminton and futsal for 1600 spectators) and concerts.\n" +
                "Access for the disabled is provided to the hall, it has a VIP area and a VIP lounge for 20 people with bathroom and WC, toilets, lobby for catering area, evacuation. Lighting – dimmable, IP addressable, LED, 1250 lux.\n" +
                "In Complex S.I.²L.A. only cultural, sports and scientific events are organized!\n" +
                "\n" +
                "Information about concerts and other cultural events\n" +
                "The entire area of \u200B\u200Bthe hall can be used for various sports, cultural and concert events. Technical data - acoustic reverberation coefficient STI-0.50, artistically dimmed, IP addressable LED lighting, 5 multimedia screens for advertising, static and mobile cameras.");

        Hall hall1 = new Hall();
        hall1.setName("Pulse");
        hall1.setCapacity(250);
        hall1.setAvailableOn(LocalDateTime.parse("2022-08-11 17:33",formatter));
        hall1.setAddress("4000 Plovdiv bul.Hristo Botev 33");
        hall1.setDescription("Pulse Fitness & Spa is the largest and most successful chain of fitness clubs in Bulgaria. With 8 consecutive annual awards for the best fitness club, 13 ultra-modern locations, over 100 highly qualified trainers and tens of thousands of club members, Pulse Fitness & Spa is a clear leader in the fitness and wellness industry.\n" +
                "But let's leave the numbers for later and tell you what Pulse Fitness & Spa really is.");

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

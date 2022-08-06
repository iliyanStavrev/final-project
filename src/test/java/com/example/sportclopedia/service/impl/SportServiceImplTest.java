package com.example.sportclopedia.service.impl;

import com.example.sportclopedia.model.dto.SportDto;
import com.example.sportclopedia.model.entity.Hall;
import com.example.sportclopedia.model.entity.Sport;
import com.example.sportclopedia.model.entity.Training;
import com.example.sportclopedia.repository.SportRepository;
import com.example.sportclopedia.repository.TrainingRepository;
import com.example.sportclopedia.service.SportService;
import com.example.sportclopedia.service.TrainingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SportServiceImplTest {

    private SportService toTest;
    private Sport sportTest;

    @Autowired
   private ModelMapper modelMapper;

    @Mock
    SportRepository sportRepository;

    @BeforeEach
    void setUp(){
        toTest = new SportServiceImpl(sportRepository,modelMapper);

        sportTest = new Sport();
        sportTest.setName("Basketball");
        sportTest.setDescription("Plovdiv 4000 Plovdiv 4000");
    }
    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void findAllSports(){

        when(sportRepository.findAll())
                .thenReturn(List.of(sportTest));

        modelMapper = new ModelMapper();

        List<SportDto> sportDtos = new ArrayList<>();

        SportDto sportDto = modelMapper.map(sportTest,SportDto.class);
        sportDtos.add(sportDto);

        Assertions.assertEquals(sportTest.getName(), sportDtos.get(0).getName());
        Assertions.assertEquals(sportTest.getDescription(), sportDtos.get(0).getDescription());
    }

    @Test
    void findByNameTest(){
        when(sportRepository.findByName(sportTest.getName()))
                .thenReturn(sportTest);

        Sport sport = toTest.findByName(sportTest.getName());

        Assertions.assertEquals(sportTest.getName(),sport.getName());
        Assertions.assertEquals(sportTest.getDescription(),sport.getDescription());

    }
}

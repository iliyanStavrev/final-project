package com.example.sportclopedia.service.impl;

import com.example.sportclopedia.model.dto.HallDto;
import com.example.sportclopedia.model.entity.Hall;
import com.example.sportclopedia.repository.HallRepository;
import com.example.sportclopedia.service.HallService;
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
public class HallServiceImplTest {

    private HallService toTest;
    private Hall hallTest;

    @Autowired
    ModelMapper modelMapper;

    @Mock
    HallRepository hallRepository;


    @BeforeEach
    void setUp(){
        toTest = new HallServiceImpl(hallRepository,modelMapper);

        hallTest = new Hall();
        hallTest.setName("Sila");
        hallTest.setAddress("Plovdiv 4000");
        hallTest.setCapacity(400);
        hallTest.setDescription("Alabalanica");

    }

    @Test
    void findByNameTest(){

        when(hallRepository.findByName(hallTest.getName()))
                .thenReturn(hallTest);

        Hall hallByName = toTest.findByName(hallTest.getName());

        Assertions.assertEquals(hallTest.getName(), hallByName.getName());
        Assertions.assertEquals(hallTest.getAddress(), hallByName.getAddress());
        Assertions.assertEquals(hallTest.getDescription(), hallByName.getDescription());
        Assertions.assertEquals(hallTest.getCapacity(), hallByName.getCapacity());
    }


    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void getAllHallsTest(){

        when(hallRepository.findAll())
                .thenReturn(List.of(hallTest));

        ModelMapper modelMapper = new ModelMapper();

        List<HallDto> hallDtos = new ArrayList<>();

        HallDto hallDto = modelMapper
                .map(hallTest, HallDto.class);
        hallDtos.add(hallDto);

        Assertions.assertEquals(hallTest.getName(), hallDtos.get(0).getName());
        Assertions.assertEquals(hallTest.getCapacity(), hallDtos.get(0).getCapacity());
        Assertions.assertEquals(hallTest.getAddress(), hallDtos.get(0).getAddress());
        Assertions.assertEquals(hallTest.getDescription(), hallDtos.get(0).getDescription());

    }
}

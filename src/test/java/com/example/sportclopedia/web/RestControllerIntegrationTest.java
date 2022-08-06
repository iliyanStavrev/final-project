package com.example.sportclopedia.web;

import com.example.sportclopedia.model.dto.SportDto;
import com.example.sportclopedia.model.entity.Sport;
import com.example.sportclopedia.repository.SportRepository;
import com.example.sportclopedia.service.SportService;
import com.example.sportclopedia.service.impl.SportServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerIntegrationTest {

    private SportService toTest;
    private Sport sportTest;
    private Sport sportTest1;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SportRepository sportRepository;
    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp(){

        toTest = new SportServiceImpl(sportRepository,modelMapper);

        sportTest = new Sport();
        sportTest1 = new Sport();
        sportTest.setName("Basketball");
        sportTest.setDescription("Basketball is nice");

        sportTest1.setName("Football");
        sportTest1.setDescription("Football is nice");

    }
    @AfterEach
    void clear(){
        sportRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN","USER"})
    void testGetAllSports() throws Exception {

        when(toTest.findAll())
                .thenReturn(List.of(sportTest,sportTest1).stream().map(
                        sport -> modelMapper
                                .map(sport, SportDto.class)
                ).toList());

        List<Sport> sports = sportRepository.findAll();

        mockMvc.perform(get("/sports/api/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$.[0].name", is(sports.get(0).getName())))
                .andExpect(jsonPath("$.[1].name", is(sports.get(1).getName())));

    }
}

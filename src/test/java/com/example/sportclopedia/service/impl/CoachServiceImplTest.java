package com.example.sportclopedia.service.impl;

import com.example.sportclopedia.model.dto.AddCoachDto;
import com.example.sportclopedia.model.dto.CoachDto;
import com.example.sportclopedia.model.entity.Coach;
import com.example.sportclopedia.model.entity.User;
import com.example.sportclopedia.model.entity.UserRole;
import com.example.sportclopedia.model.enums.UserRoleEnum;
import com.example.sportclopedia.repository.CoachRepository;
import com.example.sportclopedia.service.CoachService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CoachServiceImplTest {

    private CoachService toTest;
    private Coach testCoach;
    @Autowired
    private ModelMapper modelMapper;

    @Mock
    private CoachRepository coachRepository;

    @BeforeEach
    void setUp() {
        toTest = new CoachServiceImpl(coachRepository, modelMapper);

        testCoach = new Coach();
        testCoach.setId(1L);
        testCoach.setFullName("Georgi Georgiev");
        testCoach.setPhoneNumber("0899 99 99 99");
        testCoach.setCoachSince(LocalDate
                .parse("2004-07-07", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        testCoach.setAge(35);


    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void findByNameTest() {

        when(coachRepository.findByFullName(testCoach.getFullName()))
                .thenReturn(testCoach);

        Coach coachByName = toTest.findByName(testCoach.getFullName());

        Assertions.assertEquals(testCoach.getId(), coachByName.getId());
        Assertions.assertEquals(testCoach.getFullName(), coachByName.getFullName());
        Assertions.assertEquals(testCoach.getCoachSince(), coachByName.getCoachSince());
        Assertions.assertEquals(testCoach.getPhoneNumber(), coachByName.getPhoneNumber());
        Assertions.assertEquals(testCoach.getAge(), coachByName.getAge());
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void getAllCoachTest(){

        when(coachRepository.findAll())
                .thenReturn(List.of(testCoach));
        modelMapper = new ModelMapper();

        List<CoachDto> coachDtos = Stream.of(testCoach)
                                .map(coach -> modelMapper
                                        .map(coach,CoachDto.class))
                                        .toList();

        Assertions.assertEquals(testCoach.getFullName(),coachDtos.get(0).getFullName());
        Assertions.assertEquals(testCoach.getId(),coachDtos.get(0).getId());
        Assertions.assertEquals(testCoach.getCoachSince(),coachDtos.get(0).getCoachSince());
        Assertions.assertEquals(testCoach.getAge(),coachDtos.get(0).getAge());
        Assertions.assertEquals(testCoach.getPhoneNumber(),coachDtos.get(0).getPhoneNumber());

    }
}

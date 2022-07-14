package com.example.sportclopedia.service.impl;

import com.example.sportclopedia.error.ConstraintViolationException;
import com.example.sportclopedia.model.dto.AddCoachDto;
import com.example.sportclopedia.model.dto.CoachDto;
import com.example.sportclopedia.model.entity.Coach;
import com.example.sportclopedia.repository.CoachRepository;
import com.example.sportclopedia.service.CoachService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;
    private final ModelMapper modelMapper;

    public CoachServiceImpl(CoachRepository coachRepository, ModelMapper modelMapper) {
        this.coachRepository = coachRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCoach() {
        if (coachRepository.count() > 0){
            return;
        }
        Coach coach = new Coach();
        coach.setFullName("Iliyan Stavrev");
        coach.setCoachSince(LocalDate
                .parse("2004-07-07",DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        coach.setAge(37);
        coach.setPhoneNumber("0898 77 88 99");

        Coach coach1 = new Coach();
        coach1.setFullName("Petar Petrov");
        coach1.setCoachSince(LocalDate
                .parse("2007-08-08",DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        coach1.setAge(33);
        coach1.setPhoneNumber("0898 99 88 99");

        coachRepository.save(coach);
        coachRepository.save(coach1);
    }

    @Override
    public Coach findById(Long id) {
        return coachRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<CoachDto> getAllCoaches() {
        return coachRepository
                .findAll()
                .stream()
                .map(coach -> {
                    CoachDto coachDto = modelMapper
                            .map(coach, CoachDto.class);
                    coachDto.setCoachSince(coach.getCoachSince());
                    return coachDto;
                })
                .toList();
    }

    @Override
    public Coach findByName(String coach) {
        return coachRepository
                .findByFullName(coach);
    }

    @Override
    public List<String> getAllCoachesNames() {

        return getAllCoaches()
                .stream()
                .map(c -> String.format("%s", c.getFullName()))
                .toList();
    }

    @Override
    public void addCoach(AddCoachDto addCoachDto) {

        Coach coach = modelMapper
                .map(addCoachDto, Coach.class);

        coachRepository.save(coach);
    }

    @Override
    public void deleteCoach(Long id) {

        Coach coach = coachRepository
                .findById(id).orElse(null);

        if (!coach.getTrainings().isEmpty()){
            throw new ConstraintViolationException(coach.getId(), coach.getFullName());
        }
        coachRepository
                .deleteById(id);
    }
}

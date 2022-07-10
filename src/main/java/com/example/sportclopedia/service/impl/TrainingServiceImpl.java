package com.example.sportclopedia.service.impl;

import com.example.sportclopedia.model.dto.AddTrainingDto;
import com.example.sportclopedia.model.dto.TrainingDto;
import com.example.sportclopedia.model.entity.Training;
import com.example.sportclopedia.model.enums.IntensityLevelEnum;
import com.example.sportclopedia.repository.TrainingRepository;
import com.example.sportclopedia.service.CoachService;
import com.example.sportclopedia.service.HallService;
import com.example.sportclopedia.service.SportService;
import com.example.sportclopedia.service.TrainingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository trainingRepository;
    private final SportService sportService;
    private final CoachService coachService;
    private final HallService hallService;
    private final ModelMapper modelMapper;

    public TrainingServiceImpl(TrainingRepository trainingRepository, SportService sportService, CoachService coachService, HallService hallService, ModelMapper modelMapper) {
        this.trainingRepository = trainingRepository;
        this.sportService = sportService;
        this.coachService = coachService;
        this.hallService = hallService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initTraining() {
        if (trainingRepository.count() > 0) {
            return;
        }
        Training training = new Training();
        training.setDuration(55);
        training.setIntensity(IntensityLevelEnum.Intermediate);
        training.setName("Training for speed and agility");
        training.setStartedOn(LocalDateTime
                .parse("2021-07-07 at 19:45",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm")));
        training.setSport(sportService.findByName("Basketball"));
        training.setCoach(coachService.findById(1L));
        trainingRepository.save(training);

        Training training1 = new Training();
        training1.setDuration(75);
        training1.setIntensity(IntensityLevelEnum.High);
        training1.setName("Power moves and explosion");
        training1.setStartedOn(LocalDateTime
                .parse("2021-07-08 at 19:45",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm")));
        training1.setSport(sportService.findByName("Basketball"));
        training1.setCoach(coachService.findById(1L));
        trainingRepository.save(training1);
    }

    @Override
    public List<TrainingDto> getTrainingsBySport(String sportName) {

        List<Training> trainings = trainingRepository
                .findBySport_Name(sportName);
       return trainings
                .stream()
                .map(training -> {
                    TrainingDto trainingDto = modelMapper
                            .map(training,TrainingDto.class);
                    trainingDto.setCoach(training.getCoach().getFullName());
                    trainingDto.setSport(training.getSport().getName());
                    trainingDto.setHall(training.getHall() == null
                            ? "There is no Hall yet!"
                            : training.getHall().getName());
                    return trainingDto;
                })
                .collect(Collectors.toList());

    }

    @Override
    public void addTraining(AddTrainingDto addTrainingDto) {

        Training training = modelMapper
                .map(addTrainingDto, Training.class);

        training.setIntensity(IntensityLevelEnum.valueOf(addTrainingDto.getIntensity()));
        training.setSport(sportService.findByName(addTrainingDto.getSport()));
        training.setHall(hallService.findByName(addTrainingDto.getHall()));
        training.setCoach(coachService.findByName(addTrainingDto.getCoach()));

        trainingRepository.save(training);
    }
}

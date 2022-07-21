package com.example.sportclopedia.service.impl;

import com.example.sportclopedia.error.TrainingTimeViolation;
import com.example.sportclopedia.model.dto.AddTrainingDto;
import com.example.sportclopedia.model.dto.TrainingDto;
import com.example.sportclopedia.model.entity.Training;
import com.example.sportclopedia.model.entity.User;
import com.example.sportclopedia.model.enums.IntensityLevelEnum;
import com.example.sportclopedia.repository.TrainingRepository;
import com.example.sportclopedia.repository.UserRepository;
import com.example.sportclopedia.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserService userService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public TrainingServiceImpl(TrainingRepository trainingRepository, SportService sportService,
                               CoachService coachService, HallService hallService,
                               UserService userService, UserRepository userRepository, ModelMapper modelMapper) {
        this.trainingRepository = trainingRepository;
        this.sportService = sportService;
        this.coachService = coachService;
        this.hallService = hallService;
        this.userService = userService;
        this.userRepository = userRepository;
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
                .parse("2021-07-21 at 19:45",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm")));
        training.setSport(sportService.findByName("Basketball"));
        training.setCoach(coachService.findById(1L));
        training.setHall(hallService.findByName("SILA"));
        trainingRepository.save(training);

        Training training1 = new Training();
        training1.setDuration(75);
        training1.setIntensity(IntensityLevelEnum.High);
        training1.setName("Power moves and explosion");
        training1.setStartedOn(LocalDateTime
                .parse("2021-07-21 at 19:45",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm")));
        training1.setSport(sportService.findByName("Basketball"));
        training1.setCoach(coachService.findById(1L));
        training1.setHall(hallService.findByName("SILA"));
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
                            .map(training, TrainingDto.class);
                    trainingDto.setCoachFullName(training.getCoach().getFullName());
                    trainingDto.setSportName(training.getSport().getName());
                    trainingDto.setHallName(training.getHall() == null
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

    @Override
    public Training deleteTraining(Long id) {
        Training training = trainingRepository
                .findById(id).orElse(null);
        if (LocalDateTime.now().isAfter(training.getStartedOn())) {
            trainingRepository
                    .deleteById(id);
        } else {
            throw new TrainingTimeViolation(training.getName(), training.getStartedOn());
        }

        return training;
    }

    @Override
    public List<TrainingDto> getAllTrainings() {

        return trainingRepository
                .findAll()
                .stream()
                .map(t -> modelMapper
                        .map(t, TrainingDto.class))
                .toList();
    }

    @Override
    public boolean reserveTraining(Long id) {

        User user = getUser();
        Training training = trainingRepository
                .findById(id).orElse(null);
        List<Training> trainings = user.getTrainings();

        if (!trainings.contains(training)) {
            trainings.add(training);
            user.setTrainings(trainings);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    private User getUser() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username = ((UserDetails) principal).getUsername();
        return userService.findByUsername(username);
    }

    @Override
    public List<TrainingDto> getAllUserTrainings() {

        User user = getUser();

        return user.getTrainings()
                .stream()
                .map(t -> modelMapper
                        .map(t, TrainingDto.class))
                .toList();
    }

    @Override
    public void removeTrainingFromUser(Long id) {

        User user = getUser();
        user.getTrainings().remove(trainingRepository.findById(id).orElse(null));
        userRepository.save(user);
    }

    @Override
    public boolean isTrainingAdded(AddTrainingDto addTrainingDto) {
        Training training = trainingRepository
                .findByNameAndSport_NameAndStartedOn(addTrainingDto.getName(),
                        addTrainingDto.getSport(), addTrainingDto.getStartedOn());
        return training != null;
    }

    @Override
    public void deleteExpiredTraining() {

        List<Training> trainings = trainingRepository
                .findAll()
                .stream()
                .filter(training -> training.getStartedOn().isBefore(LocalDateTime.now()))
                .toList();

        trainingRepository.deleteAll(trainings);
    }
}

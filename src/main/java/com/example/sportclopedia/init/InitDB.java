package com.example.sportclopedia.init;

import com.example.sportclopedia.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDB implements CommandLineRunner {

    private final HallService hallService;
    private final SportService sportService;
    private final CoachService coachService;
    private final TrainingService trainingService;
    private final UserService userService;

    public InitDB(HallService hallService, SportService sportService, CoachService coachService, TrainingService trainingService, UserService userService) {
        this.hallService = hallService;
        this.sportService = sportService;
        this.coachService = coachService;
        this.trainingService = trainingService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        hallService.initHall();
        sportService.initSport();
        coachService.initCoach();
        trainingService.initTraining();
        userService.init();
    }
}

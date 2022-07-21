package com.example.sportclopedia.init;

import com.example.sportclopedia.service.TrainingService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class CleanUpTrainings {

    private final TrainingService trainingService;

    public CleanUpTrainings(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void cleanUpTrainings(){
        trainingService.deleteExpiredTraining();
    }
}

package com.example.sportclopedia.service;

import com.example.sportclopedia.model.dto.AddTrainingDto;
import com.example.sportclopedia.model.dto.TrainingDto;
import com.example.sportclopedia.model.entity.Training;

import java.util.List;

public interface TrainingService {

    void initTraining();

    List<TrainingDto> getTrainingsBySport(String sportName);

    void addTraining(AddTrainingDto addTrainingDto);

    Training deleteTraining(Long id);

    List<TrainingDto> getAllTrainings();

    boolean reserveTraining(Long id);

    List<TrainingDto> getAllUserTrainings();

    void removeTrainingFromUser(Long id);

}

package com.example.sportclopedia.web;

import com.example.sportclopedia.model.dto.AddTrainingDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TrainingController {

    @ModelAttribute
    public AddTrainingDto addTrainingDto(){
        return new AddTrainingDto();
    }

    @GetMapping("/details/{id}")
    public String detailsPage(@PathVariable String id){

        return "trainings";
    }

    @GetMapping("/trainings/add")
    public String addTrainingPage(){


        return "training-add";
    }
}

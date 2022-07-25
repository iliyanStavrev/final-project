package com.example.sportclopedia.web;

import com.example.sportclopedia.service.TrainingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final TrainingService trainingService;

    public HomeController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/")
    public String indexPage(Model model){

        model.addAttribute("bestTrainings", trainingService.getBestTrainings());

        return "index";
    }
}

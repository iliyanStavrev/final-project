package com.example.sportclopedia.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TrainingController {

    @GetMapping("/details/{id}")
    public String detailsPage(@PathVariable String id){

        return "trainings";
    }
}

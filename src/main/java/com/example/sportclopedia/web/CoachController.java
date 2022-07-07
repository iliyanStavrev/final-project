package com.example.sportclopedia.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoachController {

    @GetMapping("/coaches")
    public String coachesPage(){


        return "coaches";
    }
}

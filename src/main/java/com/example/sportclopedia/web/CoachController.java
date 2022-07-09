package com.example.sportclopedia.web;

import com.example.sportclopedia.service.CoachService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping("/coaches")
    public String coachesPage(Model model){

        model.addAttribute("coaches",coachService.getAllCoaches());

        return "coaches";
    }
}

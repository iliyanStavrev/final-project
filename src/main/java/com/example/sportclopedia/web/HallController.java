package com.example.sportclopedia.web;

import com.example.sportclopedia.service.HallService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping("/halls")
    public String hallsPage(Model model){

        model.addAttribute("halls", hallService.getAllHalls());

        return "halls";
    }
}

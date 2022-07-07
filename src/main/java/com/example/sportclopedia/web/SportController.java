package com.example.sportclopedia.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SportController {

    @GetMapping("/sports")
    public String sportsPage(){

        return "sports";
    }
}

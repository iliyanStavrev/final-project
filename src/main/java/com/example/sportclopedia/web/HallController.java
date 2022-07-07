package com.example.sportclopedia.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HallController {

    @GetMapping("/halls")
    public String hallsPage(){

        return "halls";
    }
}

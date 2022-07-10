package com.example.sportclopedia.web;

import com.example.sportclopedia.model.dto.AddHallDto;
import com.example.sportclopedia.service.HallService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @ModelAttribute
    public AddHallDto addHallDto(){
        return new AddHallDto();
    }

    @GetMapping("/halls")
    public String hallsPage(Model model){

        model.addAttribute("halls", hallService.getAllHalls());

        return "halls";
    }

    @GetMapping("/halls/add")
    public String addHallPage(){


        return "hall-add";
    }

    @PostMapping("/halls/add")
    private String addHall(@Valid AddHallDto addHallDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addHallDto", addHallDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.addHallDto"
                            ,bindingResult);

            return "redirect:/halls/add";
        }

        hallService.addHall(addHallDto);

        return "redirect:/halls";
    }
}

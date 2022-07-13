package com.example.sportclopedia.web;

import com.example.sportclopedia.model.dto.AddCoachDto;
import com.example.sportclopedia.service.CoachService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }


    @ModelAttribute
    public AddCoachDto addCoachDto(){
        return new AddCoachDto();
    }

    @GetMapping("/coaches")
    public String coachesPage(Model model){

        model.addAttribute("coaches",coachService.getAllCoaches());

        return "coaches";
    }

    @GetMapping("/coaches/add")
    public String addCoachPage(){

        return "coach-add";
    }

    @PostMapping("/coaches/add")
    public String addCoach(@Valid AddCoachDto addCoachDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addCoachDto", addCoachDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.addCoachDto"
                            ,bindingResult);

            return "redirect:/coaches/add";
        }
        coachService.addCoach(addCoachDto);

        return "redirect:/coaches";
    }

    @GetMapping("/coaches/delete/{id}")
    public String deleteCoach(@PathVariable Long id){

        coachService.deleteCoach(id);

        return "redirect:/coaches";
    }
}

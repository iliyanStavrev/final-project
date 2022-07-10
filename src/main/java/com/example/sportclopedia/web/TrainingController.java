package com.example.sportclopedia.web;

import com.example.sportclopedia.model.dto.AddTrainingDto;
import com.example.sportclopedia.service.CoachService;
import com.example.sportclopedia.service.TrainingService;
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
public class TrainingController {

    private final TrainingService trainingService;
    private final CoachService coachService;

    public TrainingController(TrainingService trainingService, CoachService coachService) {
        this.trainingService = trainingService;
        this.coachService = coachService;
    }

    @ModelAttribute
    public AddTrainingDto addTrainingDto(){
        return new AddTrainingDto();
    }

    @GetMapping("/details/{id}")
    public String detailsPage(@PathVariable String id){

        return "trainings";
    }

    @GetMapping("/trainings/add")
    public String addTrainingPage(Model model){

        model.addAttribute("coaches", coachService.getAllCoachesNames());

        return "training-add";
    }

    @PostMapping("/trainings/add")
    public String addTraining(@Valid AddTrainingDto addTrainingDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addTrainingDto", addTrainingDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.addTrainingDto"
                            ,bindingResult);

            return "redirect:/trainings/add";
        }

        trainingService.addTraining(addTrainingDto);

        return "redirect:/sports";
    }
}

package com.example.sportclopedia.web;

import com.example.sportclopedia.model.dto.AddTrainingDto;
import com.example.sportclopedia.model.entity.Training;
import com.example.sportclopedia.service.CoachService;
import com.example.sportclopedia.service.HallService;
import com.example.sportclopedia.service.SportService;
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
    private final SportService sportService;
    private final HallService hallService;

    public TrainingController(TrainingService trainingService, CoachService coachService,
                              SportService sportService, HallService hallService) {
        this.trainingService = trainingService;
        this.coachService = coachService;
        this.sportService = sportService;
        this.hallService = hallService;
    }

    @ModelAttribute
    public AddTrainingDto addTrainingDto() {
        return new AddTrainingDto();
    }

    @GetMapping("/trainings/sport/{id}")
    public String detailsPage(@PathVariable String id) {

        return "trainings-by-sports";
    }

    @GetMapping("/trainings/add")
    public String addTrainingPage(Model model) {

        model.addAttribute("coaches", coachService.getAllCoachesNames());
        model.addAttribute("sports", sportService.getAllSportsNames());
        model.addAttribute("halls", hallService.getAllHallsNames());

        return "training-add";
    }

    @PostMapping("/trainings/add")
    public String addTraining(@Valid AddTrainingDto addTrainingDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addTrainingDto", addTrainingDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.addTrainingDto"
                            , bindingResult);
            return "redirect:/trainings/add";
        }
         if (addTrainingDto.getIntensity().equals("select")) {
            redirectAttributes.addFlashAttribute("isIntensityNull", true);
            return "redirect:/trainings/add";
        }
         if (addTrainingDto.getCoach().equals("select")) {
            redirectAttributes.addFlashAttribute("isCoachNull", true);
            return "redirect:/trainings/add";
        }
        if (addTrainingDto.getSport().equals("select")) {
            redirectAttributes.addFlashAttribute("isSportNull", true);
            return "redirect:/trainings/add";
        }
        if (addTrainingDto.getHall().equals("select")) {
            redirectAttributes.addFlashAttribute("isHallNull", true);
            return "redirect:/trainings/add";
        }


        if (trainingService.isTrainingAdded(addTrainingDto)) {
            redirectAttributes.addFlashAttribute("isAdded", true);
            return "redirect:/trainings/add";
        }

        trainingService.addTraining(addTrainingDto);

        return "redirect:/trainings/all";
    }

    @GetMapping("/trainings/delete/{id}")
    public String deleteTraining(@PathVariable Long id) {

        trainingService.deleteTraining(id);

        return "redirect:/trainings/all";
    }

    @GetMapping("/trainings/all")
    public String trainingsPage(Model model) {

        model.addAttribute("trainings", trainingService.getAllTrainings());
        return "trainings-all";
    }

    @GetMapping("/trainings/user")
    public String usersTrainings(Model model) {

        model.addAttribute("userTrainings",
                trainingService.getAllUserTrainings());

        return "trainings-by-user";
    }


    @GetMapping("/trainings/reserve/{id}")
    public String reserveTraining(@PathVariable Long id,
                                  RedirectAttributes redirectAttributes) {

        if (!trainingService.reserveTraining(id)) {
            redirectAttributes.addAttribute("isAlreadyTaken", true);
            return "redirect:/trainings/all";
        }
        return "redirect:/trainings/user";

    }

    @GetMapping("/trainings/user/delete/{id}")
    public String removeTrainingFromUser(@PathVariable Long id) {

        trainingService.removeTrainingFromUser(id);

        return "redirect:/trainings/user";
    }

    @GetMapping("trainings/details/{id}")
    public String trainingDetails(@PathVariable Long id,
                                  Model model) {

        model.addAttribute("training", trainingService.findById(id));


        return "training-details";

    }
}

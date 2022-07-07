package com.example.sportclopedia.web;

import com.example.sportclopedia.model.dto.RegisterDto;
import com.example.sportclopedia.service.UserService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public RegisterDto registerDto(){
        return new RegisterDto();
    }

    @GetMapping("/login")
    public String loginPage(){


        return "login";
    }

    @GetMapping("/register")
    public String registerPage(){


        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid RegisterDto registerDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){


        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("registerDto", registerDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.registerDto"
                            ,bindingResult);

            return "redirect:/register";
        }
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("notMatch", true);
            return "redirect:/register";
        }




        userService.registerAndLogin(registerDto);

        return "redirect:/sports";
    }
    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                              String username,
                              RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute
                (UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);


        return "redirect:/login";
    }
}

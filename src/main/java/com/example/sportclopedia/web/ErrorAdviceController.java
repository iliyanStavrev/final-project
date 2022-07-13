package com.example.sportclopedia.web;

import com.example.sportclopedia.error.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorAdviceController {

    @ExceptionHandler ({ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView objectRelation(ConstraintViolationException exception){

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("id",exception.getId());
        modelAndView.addObject("name", exception.getFullName());

        return modelAndView;
    }
}

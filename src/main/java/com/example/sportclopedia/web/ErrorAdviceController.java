package com.example.sportclopedia.web;

import com.example.sportclopedia.error.ConstraintViolationException;
import com.example.sportclopedia.error.DuplicateEntryException;
import com.example.sportclopedia.error.TrainingTimeViolation;
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

        ModelAndView modelAndView = new ModelAndView("error/error-constraint");
        modelAndView.addObject("type",exception.getType());
        modelAndView.addObject("name", exception.getName());

        return modelAndView;

    }
    @ExceptionHandler ({DuplicateEntryException.class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ModelAndView duplicateEntry(DuplicateEntryException exception){

        ModelAndView modelAndView = new ModelAndView("error/error-duplicate");
        modelAndView.addObject("name", exception.getUsername());

        return modelAndView;
    }

    @ExceptionHandler ({TrainingTimeViolation.class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ModelAndView trainingIsNotPass(TrainingTimeViolation exception){

        ModelAndView modelAndView = new ModelAndView("error/error-training");
        modelAndView.addObject("name", exception.getName());
        modelAndView.addObject("date", exception.getDate());

        return modelAndView;
    }

}

package com.pentalearn.pentalearnstart.controller;

import com.pentalearn.pentalearnstart.model.Exercise.Exercise;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Wojciech on 2016-04-24.
 * This controller is responsible for multiple choice tests
 */
@Controller
public class ExerciseController {

    //TODO WHEN will add database, get exercises by id from database
    @RequestMapping(value = "/exercises", method = RequestMethod.GET)
    public ModelAndView makeExercises() {
        Exercise exercise = new Exercise();

        ModelAndView model = new ModelAndView("exercise");
        model.addObject("title", exercise.getTitle());
        model.addObject("tasks", exercise.getTasks());
        return model;
    }

    //TODO change this method, check answers, and show results
    @RequestMapping(value = "/exerciseResult", method = RequestMethod.POST)
    public String showAnswers(@ModelAttribute("SpringWeb") Exercise exercise, ModelMap model) {

        return "exerciseResult";
    }
}

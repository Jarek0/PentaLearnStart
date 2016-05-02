package com.pentalearn.pentalearnstart.controller;

import com.pentalearn.pentalearnstart.database.Course.Chapter.Exercise.ExerciseDB;
import com.pentalearn.pentalearnstart.model.Course.Chapter.Exercise.Exercise;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Wojciech on 2016-04-24.
 * This controller is responsible for multiple choice tests
 */
@Controller
@RequestMapping("/exercise")
public class ExerciseController {

    //TODO WHEN will add database, get exercises by id from database
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView makeExercises(@RequestParam("chapterNumber") int courseNumber) {
        ModelAndView model = new ModelAndView("Exercise/exercise");

        Exercise exercise = ExerciseDB.getExerciseByChapterId(courseNumber);

        model.addObject("title", exercise.getTitle());
        model.addObject("tasks", exercise.getTasks());
        return model;
    }

    //TODO change this method, check answers, and show results
    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String showAnswers(@ModelAttribute("SpringWeb") Exercise exercise, ModelMap model) {

        return "Exercise/exerciseResult";
    }
}

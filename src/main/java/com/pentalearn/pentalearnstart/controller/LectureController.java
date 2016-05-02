package com.pentalearn.pentalearnstart.controller;

import com.pentalearn.pentalearnstart.database.Lecture.LectureDB;
import com.pentalearn.pentalearnstart.model.Lecture.Lecture;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * There is learn part, after learn will be exercise
 * Created by Wojciech on 2016-05-02.
 */
@Controller
@RequestMapping("/lecture")
public class LectureController {
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView makeLecture(@RequestParam("courseNumber") int courseNumber) {
        ModelAndView mnv = new ModelAndView("Lecture/lecture");

        Lecture lecture = LectureDB.getLectureByCourseId(courseNumber);

        mnv.addObject("lecture", lecture);
        return  mnv;
    }
}

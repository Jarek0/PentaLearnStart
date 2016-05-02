package com.pentalearn.pentalearnstart.controller;

import com.pentalearn.pentalearnstart.database.Course.CourseDB;
import com.pentalearn.pentalearnstart.model.Course.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *
 * Created by Wojciech on 2016-04-30.
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    /**Main page of controller,
     * return ModelAndView with list of courses*/
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showAllCourses(){
        ModelAndView mav = new ModelAndView("Course/course");
        mav.addObject("courses", CourseDB.getAllCourses());
        return mav;
    }

    /**
     * Receive courseNumber and show course with the same id
     */
    @RequestMapping(value = "show", method = RequestMethod.POST)
    public ModelAndView moveToCourse(@RequestParam("courseNumber") int courseNumber){
        ModelAndView mav = new ModelAndView("Course/show");
        mav.addObject("course", CourseDB.getCourseById(courseNumber));
        return mav;
    }

}

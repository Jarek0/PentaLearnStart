package com.pentalearn.pentalearnstart.controller;

import com.pentalearn.pentalearnstart.database.Course.Chapter.ChapterDB;
import com.pentalearn.pentalearnstart.database.Course.CourseDB;
import com.pentalearn.pentalearnstart.model.Course.Course;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
        return new ModelAndView("Course/course", "courses", CourseDB.getAllCourses());
    }

    @RequestMapping(value = "/showChapters", method = RequestMethod.POST)
    public ModelAndView moveToCourse(@RequestParam("courseNumber") int courseNumber){
        ModelAndView mav = new ModelAndView("Course/showChapters");

        Course course = CourseDB.getCourseById(courseNumber);
        //add chapters to view
        mav.addObject("chapters", CourseDB.getChaptersByCourseId(courseNumber));
        //add name and description of course
        mav.addObject("courseName", course.getName());
        mav.addObject("courseDescription", course.getDescription());
        return mav;
    }

}

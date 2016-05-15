package pl.pollub.cs.pentalearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.pollub.cs.pentalearn.domain.Category;
import pl.pollub.cs.pentalearn.domain.Course;
import pl.pollub.cs.pentalearn.service.ChapterService;
import pl.pollub.cs.pentalearn.service.CourseService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchCourse;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Wojciech on 2016-04-30.
 */
@RestController
@RequestMapping(value = "/api/categories/{categoryId}/courses")
public class CourseListController {

    private final CourseService courseService;


    @Inject
    public CourseListController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Course> showCoursesByCategoryId(@PathVariable Long categoryId){
        return courseService.getCoursesByCategoryId(categoryId);
    }

    // JSP Nie usuwam jeszcze na wszelki wypadek //

    /*
    @RequestMapping(value = "/showChapters", method = RequestMethod.POST)
    public ModelAndView moveToCourse(@RequestParam("courseNumber") Long courseNumber){
        ModelAndView mav = new ModelAndView("Course/showChapters");

        try {
           Course course = courseService.getById(courseNumber);
            //add chapters to view
           // mav.addObject("chapters", CourseDB.getChaptersByCourseId(courseNumber));
            //add name and description of course
            mav.addObject("courseName", course.getName());
            mav.addObject("courseDescription", course.getDescription());
        } catch (NoSuchCourse noSuchCourse) {
            mav.addObject("courseName", "Blad");
            mav.addObject("courseDescription", "Blad");
        }
        finally {
            return mav;
        }

    } */

}

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
 * Created by Wojciech on 2016-04-30.
 *------------------------------------------------------------------------
 * Controllers tree:
 *                   --> Lecture
 * Course -> Chapter |
 *                   --> Exercise --> Question --> /Answer/ (?) Maybe
 * ------------------------------------------------------------------------
 */
@RestController
@RequestMapping(value = "/api/courses")
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

}

package pl.pollub.cs.pentalearn.controller;

import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentalearn.domain.Course;
import pl.pollub.cs.pentalearn.service.CourseService;

import javax.inject.Inject;
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
    public List<Course> getAllCourses(){
        return courseService.getAll();
    }
}

package pl.pollub.cs.pentalearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.pollub.cs.pentalearn.domain.Category;
import pl.pollub.cs.pentalearn.domain.Course;
import pl.pollub.cs.pentalearn.domain.createForm.CourseCreateForm;
import pl.pollub.cs.pentalearn.service.ChapterService;
import pl.pollub.cs.pentalearn.service.CourseService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchCategory;
import pl.pollub.cs.pentalearn.service.exception.NoSuchCourse;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Wojciech on 2016-04-30.
 */
@RestController
@RequestMapping(value = "/api/courses")
public class CourseController {

    private final CourseService courseService;


    @Inject
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCourse(@Valid @RequestBody CourseCreateForm courseCreateForm,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) {
            courseService.save(new Course(courseCreateForm.getName(),courseCreateForm.getDescription(),
                                    courseCreateForm.getCategory()));
    }

    @RequestMapping(value = "/{courseId}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCourse(@PathVariable Long courseId,@Valid @RequestBody CourseCreateForm courseCreateForm,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws NoSuchCourse {

        Course course=courseService.getById(courseId);
        course.setName(courseCreateForm.getName());
        course.setDescription(courseCreateForm.getDescription());
        course.setCategory(courseCreateForm.getCategory());
        courseService.update(course);
    }

    @RequestMapping(value = "/{courseId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCourse(@PathVariable Long courseId,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws NoSuchCourse {

        Course course=courseService.getById(courseId);

        courseService.delete(course);
    }

}
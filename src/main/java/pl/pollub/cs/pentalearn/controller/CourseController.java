package pl.pollub.cs.pentalearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentalearn.domain.Course;
import pl.pollub.cs.pentalearn.service.AnswerSetService;
import pl.pollub.cs.pentalearn.service.CourseService;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * *------------------------------------------------------------------------
 * Controllers tree:
 * --> Lecture
 * Course -> Chapter |
 * --> Exercise --> Question
 * |       |
 * V       V
 * UserExercise  <-> User -> Role
 * |
 * V
 * VerificationToken
 * ------------------------------------------------------------------------
 * Created by Wojciech on 2016-04-30.
 */
@RestController
@RequestMapping(value = "/api/courses")
public class CourseController {
    private final CourseService courseService;
    private final AnswerSetService answerSetService;

    @Inject
    public CourseController(CourseService courseService, AnswerSetService answerSetService) {
        this.courseService = courseService;
        this.answerSetService = answerSetService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Course> getAllCourses() throws TableIsEmptyException {
        return courseService.getAll();
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCourse(@Valid @RequestBody Course course,
                          HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse) {

        courseService.save(course);
    }

    @RequestMapping(value = "/{courseId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCourse(@PathVariable Long courseId, @Valid @RequestBody Course course1,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        Course course = courseService.getById(courseId);
        course.setName(course1.getName());
        course.setDescription(course1.getDescription());
        course.setCategory(course1.getCategory());
        courseService.update(course);
    }

    @RequestMapping(value = "/{courseId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCourse(@PathVariable Long courseId,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        Course course = courseService.getById(courseId);
        courseService.delete(course);
    }

}

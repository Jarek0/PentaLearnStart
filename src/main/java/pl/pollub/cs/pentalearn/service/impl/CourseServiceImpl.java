package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Course;
import pl.pollub.cs.pentalearn.repository.CourseRepository;
import pl.pollub.cs.pentalearn.service.CourseService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchCourseException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by pglg on 24-04-2016.
 */
@Service
@Validated
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Inject
    public CourseServiceImpl(final CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> getAll()  throws TableIsEmptyException {
        List<Course> courses = (List<Course>) courseRepository.findAll();
        if(courses.size() == 0)
            throw new TableIsEmptyException("Course");
        return courses;
    }

    @Override
    @Transactional(readOnly = true)
    public Course getById(@NotNull @Valid final Long id) throws NoSuchCourseException {
        Course existing = courseRepository.findOne(id);
        if(existing == null)
            throw new NoSuchCourseException(id);
        return existing;
    }

    @Override
    @Transactional
    public Course save(@NotNull @Valid Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public Course update(@NotNull @Valid Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void delete(@NotNull Course course) {
        courseRepository.delete(course);
    }
}

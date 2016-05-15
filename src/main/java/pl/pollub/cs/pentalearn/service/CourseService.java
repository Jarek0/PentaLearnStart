package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.domain.Course;
import pl.pollub.cs.pentalearn.service.exception.NoSuchCourse;

import java.util.List;

/**
 * Created by pglg on 12-05-2016.
 */
public interface CourseService {
    List<Course> getList();
    List<Course> getCoursesByCategoryId(long id);
    Course getById(Long id) throws NoSuchCourse;
}

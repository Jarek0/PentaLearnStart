package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Course;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

import java.util.List;

/**
 * Created by pglg on 12-05-2016.
 */
public interface CourseService {
    List<Course> getAll() throws TableIsEmptyException;
    Course getById(Long id) throws NoSuchObjectException;
    Course save(Course course);
    Course update(Course course);
    void delete(Course course);

}

package pl.pollub.cs.pentalearn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentalearn.domain.Course;

import java.util.List;

/**
 * Created by pglg on 12-05-2016.
 */
@Repository
public interface CourseRepository extends CrudRepository<Course,Long> {
    List<Course> getCoursesByCategoryId(long id);
}

package pl.pollub.cs.pentalearn.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.domain.Course;
import pl.pollub.cs.pentalearn.repository.ChapterRepository;
import pl.pollub.cs.pentalearn.repository.CourseRepository;
import pl.pollub.cs.pentalearn.service.exception.NoSuchCourse;

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
    @Transactional(readOnly =true)
    public List<Course> getList() {
        return (List<Course>) courseRepository.findAll();
    }

    //Added method here - WN
    @Override
    @Transactional(readOnly = true)
    public List<Course> getCoursesByCategoryId(long categoryId) {
        return courseRepository.getCoursesByCategoryId( categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public Course getById(@NotNull @Valid final Long id) throws NoSuchCourse {
        Course existing= courseRepository.findOne(id);
        if(existing!=null){
            return existing;
        }
        else{
            throw new NoSuchCourse("There isn't such category of question");
        }
    }

}

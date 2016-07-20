package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.domain.Course;
import pl.pollub.cs.pentalearn.repository.ChapterRepository;
import pl.pollub.cs.pentalearn.repository.CourseRepository;
import pl.pollub.cs.pentalearn.service.ChapterService;
import pl.pollub.cs.pentalearn.service.exception.ObjectHasNoItemsInTableException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by pglg on 24-04-2016.
 */
@Service
@Validated
public class ChapterServiceImpl implements ChapterService {
    private final ChapterRepository chapterRepository;
    private final CourseRepository courseRepository;

    @Inject
    public ChapterServiceImpl(final ChapterRepository chapterRepository, CourseRepository courseRepository) {
        this.chapterRepository = chapterRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chapter> getList() throws TableIsEmptyException {
        List<Chapter> chapters = (List<Chapter>) chapterRepository.findAll();
        if(chapters.size() == 0)
            throw new TableIsEmptyException("Chapter");
        return chapters;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chapter> getChaptersByCourseId(long courseId) throws NoSuchObjectException, ObjectHasNoItemsInTableException {
        List<Chapter> chapters;

        chapters = getChaptersIfCourseExist(courseId);
        CheckIfArrayIsEmpty(chapters, courseId);
        return chapters;
    }

    private List<Chapter> getChaptersIfCourseExist(long courseId) throws NoSuchObjectException{
        Course course = courseRepository.findOne(courseId);
        if(course == null) throw new NoSuchObjectException(courseId);
        return course.getChapters();
    }

    private void CheckIfArrayIsEmpty(List<Chapter> chapters, long courseId) throws ObjectHasNoItemsInTableException{
        if(chapters.size() == 0) throw new ObjectHasNoItemsInTableException(courseId);
    }


    @Override
    @Transactional(readOnly = true)
    public Chapter getById(Long id) throws NoSuchObjectException {
        Chapter chapter=chapterRepository.findOne(id);
        if(chapter == null)
            throw new NoSuchObjectException(id);
        return chapter;
    }

    @Override
    @Transactional
    public Chapter update(@NotNull @Valid Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    @Override
    @Transactional
    public void delete(@NotNull Chapter chapter) {
         chapterRepository.delete(chapter);
    }

    @Override
    @Transactional
    public Chapter save(@NotNull @Valid Chapter chapter) {
        return chapterRepository.save(chapter);
    }

}

package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.repository.ChapterRepository;
import pl.pollub.cs.pentalearn.repository.CourseRepository;
import pl.pollub.cs.pentalearn.service.ChapterService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchChapterException;
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
    public List<Chapter> getChaptersByCourseId(long courseId) throws NoSuchCourseException {
        List<Chapter> chapters =  chapterRepository.getChaptersByCourseId(courseId);
        //if(cour )
           // throw new NoSuchCourseException(courseId);
        return chapterRepository.getChaptersByCourseId(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public Chapter getById(Long id) throws NoSuchChapterException {
        Chapter chapter=chapterRepository.findOne(id);
        if(chapter == null)
            throw new NoSuchChapterException(id);
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

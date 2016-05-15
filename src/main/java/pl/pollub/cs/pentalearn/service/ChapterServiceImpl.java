package pl.pollub.cs.pentalearn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Category;
import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.repository.CategoryRepository;
import pl.pollub.cs.pentalearn.repository.ChapterRepository;
import pl.pollub.cs.pentalearn.service.exception.CategoryAlreadyExistException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchCategory;

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

    @Inject
    public ChapterServiceImpl(final ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }


    @Override
    @Transactional(readOnly =true)
    public List<Chapter> getList() {
        return (List<Chapter>) chapterRepository.findAll();
    }

    //Added method here - WN
    @Override
    @Transactional(readOnly = true)
    public List<Chapter> getChaptersByCourseId(long courseId) {
        return chapterRepository.getChaptersByCourseId(courseId);
    }

}

package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.domain.Lecture;
import pl.pollub.cs.pentalearn.repository.ChapterRepository;
import pl.pollub.cs.pentalearn.repository.LectureRepository;
import pl.pollub.cs.pentalearn.service.LectureService;
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
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;
    private final ChapterRepository chapterRepository;

    @Inject
    public LectureServiceImpl(final LectureRepository lectureRepository, final ChapterRepository chapterRepository) {
        this.lectureRepository = lectureRepository;
        this.chapterRepository = chapterRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lecture> getList() throws TableIsEmptyException {
        List<Lecture> lectures = (List<Lecture>) lectureRepository.findAll();
        if(lectures.size() == 0)
            throw new TableIsEmptyException("lecture");
        return lectures;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lecture> getLecturesByChapterId(long chapterId) throws ObjectHasNoItemsInTableException, NoSuchObjectException {
        List<Lecture> lectures;

        lectures = getLecturesIfChapterExist(chapterId);
        CheckIfArrayIsEmpty(lectures, chapterId);
        return lectures;
    }

    private List<Lecture> getLecturesIfChapterExist(long chapterId) throws NoSuchObjectException{
        Chapter chapter = chapterRepository.findOne(chapterId);
        if(chapter == null) throw new NoSuchObjectException(chapterId);
        return chapter.getLectures();
    }

    private void CheckIfArrayIsEmpty(List<Lecture> lectures, long chapterId) throws ObjectHasNoItemsInTableException{
        if(lectures.size() == 0) throw new ObjectHasNoItemsInTableException(chapterId);
    }


    @Override
    @Transactional
    public Lecture save(@NotNull @Valid Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    @Override
    @Transactional
    public Lecture update(@NotNull @Valid Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    @Override
    @Transactional
    public void delete(@NotNull Lecture lecture) {
        lectureRepository.delete(lecture);
    }

    @Override
    @Transactional(readOnly = true)
    public Lecture getById(Long id) throws NoSuchObjectException {
        Lecture lecture=lectureRepository.findOne(id);
        if(lecture==null)
            throw new NoSuchObjectException(id);
        return lecture;
    }
}

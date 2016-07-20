package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Lecture;
import pl.pollub.cs.pentalearn.repository.LectureRepository;
import pl.pollub.cs.pentalearn.service.LectureService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchChapterException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchLectureException;
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
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;

    @Inject
    public LectureServiceImpl(final LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
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
    public List<Lecture> getLecturesByChapterId(long chapterId) throws NoSuchChapterException {
        List<Lecture> lectures = lectureRepository.getLecturesByChapterId(chapterId);
       // if(lectures.size() == 0)
          //  throw new NoSuchChapterException(chapterId);
        return lectures;
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
    public Lecture getById(Long id) throws NoSuchLectureException {
        Lecture lecture=lectureRepository.findOne(id);
        if(lecture==null)
            throw new NoSuchLectureException(id);
        return lecture;
    }
}

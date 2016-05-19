package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Exercise;
import pl.pollub.cs.pentalearn.domain.Lecture;
import pl.pollub.cs.pentalearn.repository.ExerciseRepository;
import pl.pollub.cs.pentalearn.repository.LectureRepository;
import pl.pollub.cs.pentalearn.service.LectureService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchLecture;

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
    public List<Lecture> getList() {
        return (List<Lecture>) lectureRepository.findAll();
    }

    //added method here -WN
    @Override
    @Transactional(readOnly = true)
    public Lecture getLectureByChapterId(long chapterId) {
        return lectureRepository.getLectureByChapterId(chapterId);
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
    public Lecture getById(Long id) throws NoSuchLecture {
        Lecture lecture=lectureRepository.findOne(id);
        if(lecture==null) throw new NoSuchLecture("There is not such lecture with id="+id);
        else return lecture;
    }

}

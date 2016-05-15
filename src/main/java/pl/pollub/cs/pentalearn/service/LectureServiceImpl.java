package pl.pollub.cs.pentalearn.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Exercise;
import pl.pollub.cs.pentalearn.domain.Lecture;
import pl.pollub.cs.pentalearn.repository.ExerciseRepository;
import pl.pollub.cs.pentalearn.repository.LectureRepository;

import javax.inject.Inject;
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

}

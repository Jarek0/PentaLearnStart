package pl.pollub.cs.pentalearn.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Course;
import pl.pollub.cs.pentalearn.domain.Exercise;
import pl.pollub.cs.pentalearn.repository.CourseRepository;
import pl.pollub.cs.pentalearn.repository.ExerciseRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by pglg on 24-04-2016.
 */
@Service
@Validated
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Inject
    public ExerciseServiceImpl(final ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    @Transactional(readOnly =true)
    public List<Exercise> getList() {
        return (List<Exercise>) exerciseRepository.findAll();
    }

    //added method here -WN
    @Override
    @Transactional(readOnly = true)
    public Exercise getExerciseByChapterId(long chapterId) {
        return exerciseRepository.getExerciseByChapterId(chapterId);
    }

}

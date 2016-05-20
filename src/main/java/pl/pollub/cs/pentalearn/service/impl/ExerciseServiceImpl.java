package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Course;
import pl.pollub.cs.pentalearn.domain.Exercise;
import pl.pollub.cs.pentalearn.repository.CourseRepository;
import pl.pollub.cs.pentalearn.repository.ExerciseRepository;
import pl.pollub.cs.pentalearn.service.ExerciseService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchExercise;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    public List<Exercise> getExercisesByChapterId(long chapterId) {
        return exerciseRepository.getExercisesByChapterId(chapterId);
    }

    @Override
    @Transactional
    public Exercise save(@NotNull @Valid Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    @Transactional
    public Exercise update(@NotNull @Valid Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    @Transactional
    public void delete(@NotNull Exercise exercise) {
        exerciseRepository.delete(exercise);
    }

    @Override
    @Transactional(readOnly = true)
    public Exercise getById(Long id) throws NoSuchExercise {
        Exercise exercise=exerciseRepository.findOne(id);
        if(exercise==null){
            throw new NoSuchExercise("There isn't such exercise with id= "+id);
        }
        else return exercise;
    }

}

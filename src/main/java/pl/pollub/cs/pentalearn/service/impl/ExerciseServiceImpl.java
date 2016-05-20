package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Exercise;
import pl.pollub.cs.pentalearn.repository.ExerciseRepository;
import pl.pollub.cs.pentalearn.service.ExerciseService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchChapterException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchExerciseException;
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
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Inject
    public ExerciseServiceImpl(final ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> getList() throws TableIsEmptyException {
        List<Exercise> exercises = (List<Exercise>) exerciseRepository.findAll();
        if(exercises.size() == 0)
            throw new TableIsEmptyException("Exercise");
        return exercises;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> getExercisesByChapterId(long chapterId) throws NoSuchChapterException {
        List<Exercise> exercises = exerciseRepository.getExercisesByChapterId(chapterId);
        if(exercises.size() == 0)
            throw new NoSuchChapterException(chapterId);
        return exercises;
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
    public Exercise getById(Long id) throws NoSuchExerciseException {
        Exercise exercise=exerciseRepository.findOne(id);
        if(exercise==null)
            throw new NoSuchExerciseException(id);
        return exercise;
    }
}

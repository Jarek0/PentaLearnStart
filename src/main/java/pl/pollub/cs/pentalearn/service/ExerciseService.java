package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Exercise;
import pl.pollub.cs.pentalearn.service.exception.NoSuchExercise;

import java.util.List;

/**
 * Created by pglg on 12-05-2016.
 */
public interface ExerciseService {
    List<Exercise> getList();
    Exercise getExerciseByChapterId(long chapterId);
    Exercise save(Exercise exercise);
    Exercise update(Exercise exercise);
    void delete(Exercise exercise);
    Exercise getById(Long id) throws NoSuchExercise;
}

package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Exercise;
import pl.pollub.cs.pentalearn.service.exception.NoSuchChapterException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchExerciseException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;

import java.util.List;

/**
 * Created by pglg on 12-05-2016.
 */
public interface ExerciseService {
    List<Exercise> getList() throws TableIsEmptyException;
    List<Exercise> getExercisesByChapterId(long chapterId) throws NoSuchChapterException;
    Exercise save(Exercise exercise);
    Exercise update(Exercise exercise);
    void delete(Exercise exercise);
    Exercise getById(Long id) throws NoSuchExerciseException;
}

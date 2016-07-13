package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Exercise;
import pl.pollub.cs.pentalearn.service.exception.ObjectHasNoItemsInTableException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

import java.util.List;

/**
 * Created by pglg on 12-05-2016.
 */
public interface ExerciseService {
    List<Exercise> getList() throws TableIsEmptyException;
    List<Exercise> getExercisesByChapterId(long chapterId) throws NoSuchObjectException, ObjectHasNoItemsInTableException;
    Exercise save(Exercise exercise);
    Exercise update(Exercise exercise);
    void delete(Exercise exercise);
    Exercise getById(Long id) throws NoSuchObjectException;
}

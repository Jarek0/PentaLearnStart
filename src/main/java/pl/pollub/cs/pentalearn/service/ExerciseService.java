package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Exercise;

import java.util.List;

/**
 * Created by pglg on 12-05-2016.
 */
public interface ExerciseService {
    List<Exercise> getList();
    Exercise getExerciseByChapterId(long chapterId);
}

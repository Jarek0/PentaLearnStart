package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.UserExercise;

/**
 * Created by pglg on 04-06-2016.
 */
public interface UserExerciseService  {
    UserExercise getById(Long id);
    UserExercise save(UserExercise userExercise);
}

package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.UserExercise;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

/**
 * Created by pglg on 04-06-2016.
 */
public interface UserExerciseService {
    UserExercise getById(Long id) throws NoSuchObjectException;

    UserExercise save(UserExercise userExercise);
}

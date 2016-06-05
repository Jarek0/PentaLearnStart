package pl.pollub.cs.pentalearn.service.exception;

/**
 * Created by Wojciech on 2016-06-05.
 */
public class NoSuchUserExerciseException extends Exception {
    public NoSuchUserExerciseException(Long userExerciseId) {
        super("There is no user's exercise of this ID: " + userExerciseId);
    }
}

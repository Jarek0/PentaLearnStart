package pl.pollub.cs.pentalearn.service.exception;

/**
 * Created by pglg on 19-05-2016.
 */
public class NoSuchExerciseException extends Exception {
    public NoSuchExerciseException(Long exerciseId) {
        super("There is no exercise of this ID: " + exerciseId);
    }
}

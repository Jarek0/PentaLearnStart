package pl.pollub.cs.pentalearn.service.exception;

/**
 * Created by pglg on 12-05-2016.
 */
public class NoSuchCourseException extends Exception {
    public NoSuchCourseException(Long courseId) {
        super("There is no course of this ID: " + courseId);
    }
}

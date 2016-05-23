package pl.pollub.cs.pentalearn.service.exception;

/**
 * Created by pglg on 19-05-2016.
 */
public class NoSuchQuestionException extends Exception {
    public NoSuchQuestionException(long questionId) {
        super("There is no exercise of this ID: " + questionId);
    }
}

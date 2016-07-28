package pl.pollub.cs.pentalearn.service.exception;

/**
 * Created by pglg on 25-07-2016.
 */
public class InvalidAnswerSetException extends Exception {
    public InvalidAnswerSetException() {
        super("AnswerSet is invalid");
    }
}

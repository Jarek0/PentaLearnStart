package pl.pollub.cs.pentalearn.service.exception;

/**
 * Created by pglg on 25-07-2016.
 */
public class IncompatibleAnswerSetException extends Exception {
    public IncompatibleAnswerSetException() {
        super("This AnswerSet don't match to this question");
    }
}

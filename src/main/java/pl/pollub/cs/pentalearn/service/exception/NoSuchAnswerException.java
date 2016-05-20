package pl.pollub.cs.pentalearn.service.exception;

/**
 * Created by pglg on 29-04-2016.
 */
public class NoSuchAnswerException extends Exception {
    public NoSuchAnswerException(long answerId) {
        super("There is no answer of this ID: " + answerId);
    }
}

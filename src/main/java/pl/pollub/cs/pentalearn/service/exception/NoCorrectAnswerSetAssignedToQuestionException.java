package pl.pollub.cs.pentalearn.service.exception;

/**
 * Created by pglg on 28-07-2016.
 */
public class NoCorrectAnswerSetAssignedToQuestionException extends Exception {
    public NoCorrectAnswerSetAssignedToQuestionException() {
        super("In this Exercise there is question without correct AnswerSet");
    }
}

package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.AnswerSet;
import pl.pollub.cs.pentalearn.domain.Question;
import pl.pollub.cs.pentalearn.domain.UserExercise;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

/**
 * Created by pglg on 28-04-2016.
 */
public interface AnswerSetService {
    AnswerSet save(AnswerSet answer);
    AnswerSet getById(Long id) throws NoSuchObjectException;
    AnswerSet update(AnswerSet answer);
    void delete(AnswerSet answer);
    AnswerSet getAnswerSetForQuestionInUserExercise(Question question, UserExercise userExercise);

}

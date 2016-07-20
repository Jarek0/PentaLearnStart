package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Answer;
import pl.pollub.cs.pentalearn.domain.AnswerSet;
import pl.pollub.cs.pentalearn.service.exception.NoSuchAnswerException;

/**
 * Created by pglg on 28-04-2016.
 */
public interface AnswerSetService {
    AnswerSet save(AnswerSet answer);
    AnswerSet getById(Long id) throws NoSuchAnswerException;
    AnswerSet update(AnswerSet answer);
    void delete(AnswerSet answer);

}

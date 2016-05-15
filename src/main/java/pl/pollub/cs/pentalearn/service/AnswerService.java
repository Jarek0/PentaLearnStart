package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Answer;
import pl.pollub.cs.pentalearn.service.exception.NoSuchAnswer;

/**
 * Created by pglg on 28-04-2016.
 */
public interface AnswerService {
    Answer save(Answer answer);
    Answer getById(Long id) throws NoSuchAnswer;

}

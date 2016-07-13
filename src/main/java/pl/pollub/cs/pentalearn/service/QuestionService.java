package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Question;
import pl.pollub.cs.pentalearn.service.exception.ObjectHasNoItemsInTableException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

import java.util.List;

/**
 * Created by pglg on 25-04-2016.
 */
public interface QuestionService {
    Question save(Question question) ;
    List<Question> getList() throws TableIsEmptyException;
    Question update(Question question);
    void delete(Question question);
    Question getById(Long id)throws NoSuchObjectException;
    List<Question> getQuestionsByExerciseId(long exerciseId) throws NoSuchObjectException, ObjectHasNoItemsInTableException;
}

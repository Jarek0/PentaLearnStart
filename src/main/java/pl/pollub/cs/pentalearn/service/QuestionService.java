package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Question;
import pl.pollub.cs.pentalearn.service.exception.NoSuchExerciseException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchQuestionException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;

import java.util.List;

/**
 * Created by pglg on 25-04-2016.
 */
public interface QuestionService {
    Question save(Question question) ;
    List<Question> getList() throws TableIsEmptyException;
    Question update(Question question);
    void delete(Question question);
    Question getById(Long id)throws NoSuchQuestionException;
    List<Question> getQuestionsByExerciseId(long exerciseId) throws NoSuchExerciseException;
}

package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Question;
import pl.pollub.cs.pentalearn.service.exception.NoSuchExercise;
import pl.pollub.cs.pentalearn.service.exception.NoSuchQuestion;

import java.util.List;

/**
 * Created by pglg on 25-04-2016.
 */
public interface QuestionService {

    Question save(Question question) ;
    List<Question> getList();
    Question update(Question question);
    void delete(Question question);
    Question getById(Long id)throws NoSuchQuestion;
    List<Question> getQuestionsByExerciseId(long exerciseId) throws NoSuchExercise;
}

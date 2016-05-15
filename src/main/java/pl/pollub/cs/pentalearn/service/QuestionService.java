package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Question;

import java.util.List;

/**
 * Created by pglg on 25-04-2016.
 */
public interface QuestionService {

    Question save(Question question) ;
    List<Question> getList();
}

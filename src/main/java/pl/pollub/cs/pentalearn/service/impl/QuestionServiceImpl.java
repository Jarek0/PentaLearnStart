package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Question;
import pl.pollub.cs.pentalearn.repository.QuestionRepository;
import pl.pollub.cs.pentalearn.service.QuestionService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchQuestion;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by pglg on 25-04-2016.
 */
@Service
@Validated
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Inject
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    @Transactional
    public Question save(@NotNull @Valid final Question question) {
        return questionRepository.save(question);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Question> getList() {
        return (List<Question>) questionRepository.findAll();
    }

    @Override
    @Transactional
    public Question update(Question question) {
        return questionRepository.save(question);
    }

    @Override
    @Transactional
    public void delete(Question question) {
        questionRepository.delete(question);
    }

    @Override
    @Transactional(readOnly = true)
    public Question getById(Long id) throws NoSuchQuestion {
        Question question=questionRepository.findOne(id);
        if(question==null) throw new NoSuchQuestion("There is not such question with id="+id);
        else return question;
    }
}

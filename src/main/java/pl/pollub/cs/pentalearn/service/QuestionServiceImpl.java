package pl.pollub.cs.pentalearn.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Question;
import pl.pollub.cs.pentalearn.repository.QuestionRepository;

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
}

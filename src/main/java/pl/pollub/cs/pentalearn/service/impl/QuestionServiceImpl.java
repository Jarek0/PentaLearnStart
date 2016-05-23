package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Question;
import pl.pollub.cs.pentalearn.repository.QuestionRepository;
import pl.pollub.cs.pentalearn.service.QuestionService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchExerciseException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchQuestionException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;

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
    public List<Question> getList() throws TableIsEmptyException {
        List<Question> questions = (List<Question>) questionRepository.findAll();
        if(questions.size() == 0)
            throw new TableIsEmptyException("question");
        return questions;
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
    public Question getById(Long id) throws NoSuchQuestionException {
        Question question=questionRepository.findOne(id);
        if(question == null) throw new NoSuchQuestionException(id);
        return question;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Question> getQuestionsByExerciseId(long exerciseId) throws NoSuchExerciseException {
        List<Question> questions = questionRepository.getQuestionsByExerciseId(exerciseId);
        if(questions.size() == 0)
            throw new NoSuchExerciseException(exerciseId);
        return questions;
    }
}

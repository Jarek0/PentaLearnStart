package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Answer;
import pl.pollub.cs.pentalearn.repository.AnswerRepository;
import pl.pollub.cs.pentalearn.service.AnswerService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchAnswerException;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by pglg on 28-04-2016.
 */
@Service
@Validated
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    @Inject
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    @Transactional
    public Answer save(@NotNull @Valid final Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    @Transactional(readOnly = true)
    public Answer getById(@NotNull @Valid Long id) throws NoSuchAnswerException {
        Answer existing=answerRepository.findOne(id);
        if(existing!=null){
            return existing;
        }
        else{
            throw new NoSuchAnswerException(id);
        }
    }

    @Override
    @Transactional
    public Answer update(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    @Transactional
    public void delete(Answer answer) {
         answerRepository.delete(answer);
    }
}

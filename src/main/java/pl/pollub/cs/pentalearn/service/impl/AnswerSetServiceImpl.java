package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Answer;
import pl.pollub.cs.pentalearn.domain.AnswerSet;
import pl.pollub.cs.pentalearn.repository.AnswerSetRepository;
import pl.pollub.cs.pentalearn.service.AnswerSetService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchAnswerException;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by pglg on 28-04-2016.
 */
@Service
@Validated
public class AnswerSetServiceImpl implements AnswerSetService {
    private final AnswerSetRepository answerSetRepository;

    @Inject
    public AnswerSetServiceImpl(AnswerSetRepository answerSetRepository) {
        this.answerSetRepository = answerSetRepository;
    }

    @Override
    @Transactional
    public AnswerSet save(@NotNull @Valid final AnswerSet answer) {
        return answerSetRepository.save(answer);
    }

    @Override
    @Transactional(readOnly = true)
    public AnswerSet getById(@NotNull @Valid Long id) throws NoSuchAnswerException {
        AnswerSet existing= answerSetRepository.findOne(id);
        if(existing!=null){
            return existing;
        }
        else{
            throw new NoSuchAnswerException(id);
        }
    }

    @Override
    @Transactional
    public AnswerSet update(AnswerSet answer) {
        return answerSetRepository.save(answer);
    }

    @Override
    @Transactional
    public void delete(AnswerSet answer) {
         answerSetRepository.delete(answer);
    }
}

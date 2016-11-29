package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.AnswerSet;
import pl.pollub.cs.pentalearn.domain.Question;
import pl.pollub.cs.pentalearn.domain.UserExercise;
import pl.pollub.cs.pentalearn.repository.AnswerSetRepository;
import pl.pollub.cs.pentalearn.service.AnswerSetService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    public AnswerSet getById(@NotNull @Valid Long id) throws NoSuchObjectException {
        AnswerSet existing = answerSetRepository.findOne(id);
        if (existing == null) throw new NoSuchObjectException(id);
        return existing;
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

    @Override
    @Transactional(readOnly = true)
    public AnswerSet getAnswerSetForQuestionInUserExercise(@NotNull  Question question, @NotNull  UserExercise userExercise) {
        List<AnswerSet> answerSets=answerSetRepository.findByQuestionIdAndUserExerciseId(question.getId(),userExercise.getId());
        if(answerSets.size()>=1) return answerSets.get(0);
        else return null;

    }
}

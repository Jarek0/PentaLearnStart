package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.UserExerciseResult;
import pl.pollub.cs.pentalearn.repository.UserExerciseResultRepository;
import pl.pollub.cs.pentalearn.service.UserExerciseResultService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by pglg on 27-10-2016.
 */
@Service
@Validated
public class UserExerciseResultServiceImpl implements UserExerciseResultService {

    private final UserExerciseResultRepository resultRepository;

    @Inject
    public UserExerciseResultServiceImpl(UserExerciseResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    @Transactional
    public UserExerciseResult save(@Valid @NotNull UserExerciseResult result) {
        return resultRepository.save(result);
    }
}

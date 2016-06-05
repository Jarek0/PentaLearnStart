package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.UserExercise;
import pl.pollub.cs.pentalearn.repository.UserExerciseRepository;
import pl.pollub.cs.pentalearn.service.UserExerciseService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchUserExerciseException;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by pglg on 04-06-2016.
 */
@Service
@Validated
public class UserExerciseServiceImpl implements UserExerciseService {

    private final UserExerciseRepository userExerciseRepository;

    @Inject
    public UserExerciseServiceImpl(UserExerciseRepository userExerciseRepository) {
        this.userExerciseRepository = userExerciseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserExercise getById(Long id) throws NoSuchUserExerciseException {
        UserExercise userExercise = userExerciseRepository.findOne(id);
        if(userExercise == null)
            throw new NoSuchUserExerciseException(id);
        return userExercise;
    }

    @Override
    @Transactional
    public UserExercise save(@NotNull @Valid UserExercise userExercise) {
        return userExerciseRepository.save(userExercise);
    }
}

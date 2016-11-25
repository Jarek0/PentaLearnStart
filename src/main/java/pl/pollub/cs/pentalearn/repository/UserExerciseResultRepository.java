package pl.pollub.cs.pentalearn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentalearn.domain.UserExerciseResult;

/**
 * Created by pglg on 27-10-2016.
 */
@Repository
public interface UserExerciseResultRepository extends CrudRepository<UserExerciseResult, Long> {
}

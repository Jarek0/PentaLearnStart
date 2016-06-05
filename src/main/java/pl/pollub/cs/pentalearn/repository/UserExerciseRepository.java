package pl.pollub.cs.pentalearn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentalearn.domain.UserExercise;

/**
 * Created by pglg on 04-06-2016.
 */
@Repository
public interface UserExerciseRepository extends CrudRepository<UserExercise,Long> {
}

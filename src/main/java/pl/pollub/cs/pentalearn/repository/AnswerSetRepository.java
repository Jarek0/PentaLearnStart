package pl.pollub.cs.pentalearn.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentalearn.domain.AnswerSet;
import pl.pollub.cs.pentalearn.domain.Question;
import pl.pollub.cs.pentalearn.domain.UserExercise;

import java.util.List;

/**
 * Created by pglg on 28-04-2016.
 */
@Repository
public interface AnswerSetRepository extends CrudRepository<AnswerSet, Long> {

    List<AnswerSet> findByQuestionIdAndUserExerciseId(Long questionId, Long userExerciseId);

}

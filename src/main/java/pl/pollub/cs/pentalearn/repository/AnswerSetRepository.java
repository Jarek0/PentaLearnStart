package pl.pollub.cs.pentalearn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentalearn.domain.AnswerSet;

/**
 * Created by pglg on 28-04-2016.
 */
@Repository
public interface AnswerSetRepository extends CrudRepository<AnswerSet,Long> {
}

package pl.pollub.cs.pentalearn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentalearn.domain.Question;

import javax.persistence.Entity;

/**
 * Created by pglg on 25-04-2016.
 */
@Repository
public interface QuestionRepository extends CrudRepository<Question,Long>{

}

package pl.pollub.cs.pentalearn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentalearn.domain.Category;

/**
 * Created by pglg on 24-04-2016.
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category,Long>{
    Category findByName(String name);
    Category findById(Long id);
}

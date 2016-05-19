package pl.pollub.cs.test.service;

import pl.pollub.cs.test.domain.Category;
import pl.pollub.cs.test.service.exception.NoSuchCategory;
import pl.pollub.cs.test.service.exception.CategoryAlreadyExistException;

import java.util.List;

/**
 * Created by pglg on 24-04-2016.
 */
public interface CategoryService {

    Category save(Category category) throws CategoryAlreadyExistException;
    List<Category> getList();
    Category getById(Long id) throws NoSuchCategory;
}


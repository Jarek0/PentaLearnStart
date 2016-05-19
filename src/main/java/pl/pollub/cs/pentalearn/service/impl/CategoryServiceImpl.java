package pl.pollub.cs.pentalearn.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Category;
import pl.pollub.cs.pentalearn.repository.CategoryRepository;
import pl.pollub.cs.pentalearn.service.CategoryService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchCategory;
import pl.pollub.cs.pentalearn.service.exception.CategoryAlreadyExistException;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by pglg on 24-04-2016.
 */
@Service
@Validated
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Inject
    public CategoryServiceImpl(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Category save(@NotNull @Valid final Category category) throws CategoryAlreadyExistException {
        Category existing = categoryRepository.findByName(category.getName());
        if (existing != null) {
            throw new CategoryAlreadyExistException(
                   "There already exists a category with name="+ category.getName());
        }
        return categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly =true)
    public List<Category> getList() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Category getById(@NotNull @Valid final Long id) throws NoSuchCategory {
        Category existing= categoryRepository.findById(id);
        if(existing!=null){
            return existing;
        }
        else{
            throw new NoSuchCategory("There isn't such category of question");
        }
    }
}

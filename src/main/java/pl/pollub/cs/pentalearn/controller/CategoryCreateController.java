package pl.pollub.cs.pentalearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.pollub.cs.pentalearn.domain.Category;
import pl.pollub.cs.pentalearn.domain.QuestionCategoryCreateForm;
import pl.pollub.cs.pentalearn.service.CategoryService;
import pl.pollub.cs.pentalearn.service.exception.CategoryAlreadyExistException;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by pglg on 24-04-2016.
 */
@Controller
public class CategoryCreateController {

    private final CategoryService categoryService;

    @Inject
    public CategoryCreateController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
    }

    @RequestMapping(value = "/question_category_create.html", method = RequestMethod.GET)
    public ModelAndView getQuestionCategoryCreateView() {
        return new ModelAndView("question_category_create", "form", new QuestionCategoryCreateForm());
    }

    @RequestMapping(value = "/question_category_create.html", method = RequestMethod.POST)
    public String createQuestionCategory(@Valid @ModelAttribute("form") QuestionCategoryCreateForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "question_category_create";
        }
        try {
            categoryService.save(new Category(form.getName()));
        } catch (CategoryAlreadyExistException e) {
            result.reject("question.category.error.exist");
            return "question_category_create";
        }
        return "redirect:/question_category_list.html";
    }

}

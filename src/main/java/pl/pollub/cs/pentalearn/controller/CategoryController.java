package pl.pollub.cs.pentalearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.pollub.cs.pentalearn.domain.Category;
import pl.pollub.cs.pentalearn.service.CategoryService;
import pl.pollub.cs.pentalearn.service.exception.CategoryAlreadyExistException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by pglg on 25-04-2016.
 */
/*POTEM ZOBACZYSZ DŁUGIE REQUEST MAPPINGI, GADAŁEM Z KRZYŚKIEM MÓWIŁ
 ŻE TAK JEST 100% PURE REST ALE DZIWNIE BO NIEKTÓRE ID DALEJ SĄ NIEUŻYWANE
 TO JEST DO POPRAWY NIECH KRZYSIEK SPOJRZY NA KOD I SIĘ WYPOWIE
  */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Inject
    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> showAllCategories() {
        return categoryService.getList();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCategory(@Valid @RequestBody Category category, HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse) throws CategoryAlreadyExistException {

        categoryService.save(category);
    }

}

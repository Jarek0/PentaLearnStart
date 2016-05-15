package pl.pollub.cs.pentalearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.pollub.cs.pentalearn.domain.*;
import pl.pollub.cs.pentalearn.service.AnswerService;
import pl.pollub.cs.pentalearn.service.CategoryService;
import pl.pollub.cs.pentalearn.service.QuestionService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchCategory;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pglg on 25-04-2016.
 */

/*TE LINIJKII WYRZUCAJA BLAD BO BRAKUJE ELEMENTOW DO KONSTRUKTORA A TO WIAZE SIE NARAZIE Z
NAPISANIEM JSP (DOSC ROZBUDOWANEGO) I POBRANIEM TEGO OD URZYTKOWNIKA. PRZY ZMIANIE NA REST
TO POWINNO SIE LATWO NAPRAWIC.
 */





@Controller
public class QuestionCreateController {


    private final QuestionService questionService;
    private final CategoryService categoryService;
    private final AnswerService answerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateController.class);


    @Inject
    public QuestionCreateController(QuestionService questionService, CategoryService categoryService, AnswerService answerService) {
        this.questionService = questionService;
        this.categoryService = categoryService;
        this.answerService = answerService;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
       // binder.addValidators(questionCreateFormQuestionCategoryIdValidator);
    }


    public ModelAndView getQuestionCreateView(QuestionCreateForm questionCreateForm) {
        ModelMap model = new ModelMap();
        model.addAttribute("questionCategories", categoryService.getList());
        model.addAttribute("form",questionCreateForm);
        return new ModelAndView("question_create", model);
    }

    @RequestMapping(value = "/question_create.html", method = RequestMethod.GET)
    public ModelAndView getQuestionCreateView() {
        QuestionCreateForm questionCreateForm=new QuestionCreateForm();
        questionCreateForm.setAnswersNumber(3);
        return getQuestionCreateView(questionCreateForm);
    }


    @RequestMapping(value = "/question_create.html", method = RequestMethod.POST)
    public ModelAndView createQuestion(@ModelAttribute("form") @Valid QuestionCreateForm form,
                                      /* @ModelAttribute("answers") @Valid AnswersCreateForm answersCreateForm,*/
                                       BindingResult result) {
        if(form.getAnswersNumberChanged()){
            return getQuestionCreateView(form);
        }
        if (result.hasErrors()) {

            ModelMap model = new ModelMap();
            model.addAttribute("questionCategories", categoryService.getList());
            model.addAttribute("form");

            return new ModelAndView("question_create", model);

        }
        else {
            /*W TYM BLOKU SA TE DWIE LINIJKI O KTORYCH PISALEM WYZEJ. KOMENTUJE TO ABY SIE
            * KOMPILOWALO ZEBY MOC ZOBACZYC ZMIANY W STRUKTURZE BAZY DANYCH*/


           /* try {
                Category category = categoryService.getById(form.getQuestionCategoryId());
                Question question = new Question(form.getQuestionText(), category);
                questionService.save(question);
                category.addQuestion(question);

                List<Answer> answers = new ArrayList<>();
                for (int i = 0; i < form.getAnswerTexts().size(); i++) {
                    Answer answer = new Answer(question, form.getAnswerTexts().get(i),
                            form.getCorrects().get(i));
                    answers.add(answer);
                    answerService.save(answer);
                }
                question.setAnswers(answers);


            } catch (NoSuchCategory e) {
                result.reject("question.error");

                ModelMap model = new ModelMap();
                model.addAttribute("questionCategories", categoryService.getList());
                model.addAttribute("form");
                return new ModelAndView("question_create", model);
            }*/


            return new ModelAndView("redirect:/question_category_list.html");
        }

    }

}

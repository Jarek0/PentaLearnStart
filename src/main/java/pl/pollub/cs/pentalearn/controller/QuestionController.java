package pl.pollub.cs.pentalearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentalearn.domain.*;
import pl.pollub.cs.pentalearn.domain.createForm.AnswerCreateForm;
import pl.pollub.cs.pentalearn.domain.createForm.LectureCreateForm;
import pl.pollub.cs.pentalearn.domain.createForm.QuestionCreateForm;
import pl.pollub.cs.pentalearn.service.AnswerService;
import pl.pollub.cs.pentalearn.service.CategoryService;
import pl.pollub.cs.pentalearn.service.ExerciseService;
import pl.pollub.cs.pentalearn.service.QuestionService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchExercise;
import pl.pollub.cs.pentalearn.service.exception.NoSuchLecture;
import pl.pollub.cs.pentalearn.service.exception.NoSuchQuestion;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by pglg on 25-04-2016.
 */






@RestController
@RequestMapping(value = "/api/exercises/{exerciseId}/questions")
public class QuestionController {


    private final QuestionService questionService;
    private final AnswerService answerService;
    private final ExerciseService exerciseService;


    @Inject
    public QuestionController(QuestionService questionService, CategoryService categoryService, AnswerService answerService, ExerciseService exerciseService) {
        this.questionService = questionService;
        this.exerciseService = exerciseService;
        this.answerService = answerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addQuestion(@PathVariable Long exerciseId, @Valid @RequestBody QuestionCreateForm questionCreateForm,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) throws  NoSuchExercise {

        Exercise exercise=exerciseService.getById(exerciseId);
        Question question=new Question(questionCreateForm.getQuestionText(),exercise);
        questionService.save(question);
        for(AnswerCreateForm answerCreateForms: questionCreateForm.getAnswers()){
            Answer answer=new Answer(question,answerCreateForms.getAnswerText(),answerCreateForms.getCorrect());
            answerService.save(answer);
        }
    }

    @RequestMapping(value = "/{questionId}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateQuestion(@PathVariable Long questionId,@Valid @RequestBody QuestionCreateForm questionCreateForm,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws NoSuchQuestion {

        Question question=questionService.getById(questionId);
        question.setQuestionText(questionCreateForm.getQuestionText());
        int i=0;
        for(;i<questionCreateForm.getAnswers().size();i++){
           Answer answer=question.getAnswers().get(i);
            AnswerCreateForm answerCreateForm=questionCreateForm.getAnswers().get(i);
            answer.setAnswerText(answerCreateForm.getAnswerText());
            answer.setCorrect(answerCreateForm.getCorrect());
            answerService.update(answer);
        }
        for(;i<question.getAnswers().size();i++){
            Answer answer=question.getAnswers().get(i);
            answerService.delete(answer);
        }
        questionService.save(question);
    }






}
package pl.pollub.cs.pentalearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentalearn.domain.*;
import pl.pollub.cs.pentalearn.service.AnswerSetService;
import pl.pollub.cs.pentalearn.service.ExerciseService;
import pl.pollub.cs.pentalearn.service.QuestionService;
import pl.pollub.cs.pentalearn.service.exception.InvalidAnswerSetException;
import pl.pollub.cs.pentalearn.service.exception.ObjectHasNoItemsInTableException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by pglg on 25-04-2016.
 */

@RestController
@RequestMapping(value = "/api/exercises/{exerciseId}/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final AnswerSetService answerSetService;
    private final ExerciseService exerciseService;

    @Inject
    public QuestionController(QuestionService questionService, AnswerSetService answerSetService,
                              ExerciseService exerciseService) {
        this.questionService = questionService;
        this.exerciseService = exerciseService;
        this.answerSetService = answerSetService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Question> showQuestionsByExerciseId(@PathVariable long exerciseId)
            throws NoSuchObjectException, ObjectHasNoItemsInTableException {
        return questionService.getQuestionsByExerciseId(exerciseId);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addQuestion(@PathVariable Long exerciseId, @Valid @RequestBody Question question,
                            HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        Exercise exercise = exerciseService.getById(exerciseId);
        Question question1 = new Question(question.getQuestionText(), exercise);
        questionService.save(question1);
    }

    @RequestMapping(value = "/{questionId}/answers", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateQuestionCorrectAnswerSet(@PathVariable Long questionId, @Valid @RequestBody AnswerSet answerSet,
                                               HttpServletRequest httpServletRequest,
                                               HttpServletResponse httpServletResponse) throws NoSuchObjectException, InvalidAnswerSetException {

        Question question = questionService.getById(questionId);
        List<String> texts = answerSet.getTexts();
        List<Boolean> answers = answerSet.getAnswers();
        AnswerSet currentAnswerSet = question.getCorrectAnswerSet();
        currentAnswerSet.setAnswers(answers);
        currentAnswerSet.setTexts(texts);
        currentAnswerSet.setMultiSelectAllowed(answerSet.getMultiSelectAllowed());
        answerSetService.save(currentAnswerSet);

    }

    @RequestMapping(value = "/{questionId}/answers", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addQuestionCorrectAnswerSet(@PathVariable Long questionId, @Valid @RequestBody AnswerSet answerSet) throws NoSuchObjectException, InvalidAnswerSetException {
        Question question = questionService.getById(questionId);
        AnswerSet answerSet1 = new AnswerSet(answerSet.getTexts(), answerSet.getAnswers(), answerSet.getMultiSelectAllowed(), question);
        answerSetService.save(answerSet1);
    }

    @RequestMapping(value = "/{questionId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateQuestion(@PathVariable Long questionId, @PathVariable Long exerciseId, @Valid @RequestBody Question question,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) throws NoSuchObjectException, InvalidAnswerSetException {

        Exercise exercise = exerciseService.getById(exerciseId);
        Question question1 = questionService.getById(questionId);

        question1.setQuestionText(question.getQuestionText());

        question1.setExercise(exercise);

        questionService.save(question1);
    }

}

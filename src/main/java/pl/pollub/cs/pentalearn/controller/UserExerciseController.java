package pl.pollub.cs.pentalearn.controller;

import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentalearn.domain.*;
import pl.pollub.cs.pentalearn.service.AnswerSetService;
import pl.pollub.cs.pentalearn.service.ExerciseService;
import pl.pollub.cs.pentalearn.service.QuestionService;
import pl.pollub.cs.pentalearn.service.UserExerciseService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Wojciech on 2016-06-04.
 */
@RestController
@RequestMapping("api/")
public class UserExerciseController {
    private final ExerciseService exerciseService;
    private final UserExerciseService userExerciseService;
    private final QuestionService questionService;
    private final AnswerSetService answerSetService;
    @Inject
    UserExerciseController(ExerciseService exerciseService, UserExerciseService userExerciseService, QuestionService questionService, AnswerSetService answerSetService){
        this.exerciseService = exerciseService;
        this.userExerciseService = userExerciseService;
        this.questionService = questionService;
        this.answerSetService = answerSetService;
    }

    //Create userTest and send Exercise (Test)
    @RequestMapping(value = "exercises/{exerciseId}/start", method = RequestMethod.GET)
    public UserExercise startExerciseById(@PathVariable Long exerciseId) throws NoSuchObjectException{
        Exercise exercise=exerciseService.getById(exerciseId);
        UserExercise userExercise = new UserExercise(exercise);
        userExerciseService.save(userExercise);
        return userExercise;
    }

    //id of question cant be in answerSet
    @RequestMapping(value = "userExercises/{userExerciseId}/{questionId}", method = RequestMethod.POST)
    public void addUserAnswer(@PathVariable Long userExerciseId ,@PathVariable Long questionId ,@Valid @RequestBody AnswerSet answerSet)
            throws NoSuchObjectException {
        UserExercise exercise=userExerciseService.getById(userExerciseId);
        Question question=questionService.getById(questionId);

        //dont have question because in this case we dont have to make additional field in
        //Question that will represent user answers. maybe it will be change in the future
        AnswerSet answerSet1=new AnswerSet(exercise,answerSet.getTexts(),answerSet.getAnswers());
        answerSetService.save(answerSet1);

    }

    //zakładam że nie było śmieszka i test rozwiązany do końca
    //zwraca string. nic lepszego nei wymyśliłem
    @RequestMapping(value = "userExercises/{userExerciseId}/stop",method = RequestMethod.GET)
    public String  showResults(@PathVariable Long userExerciseId)  throws NoSuchObjectException {
       /* StringBuilder sb = new StringBuilder();
        UserExercise userExercise = userExerciseService.findById(userExerciseId);
        List<AnswerSet> answerSet = userExercise.getAnswerSets();
        for (int i=0; i<answerSet.size(); i++){
            sb.append("QustionId: "); sb.append(answerSet.get(i).getQuestion().getId()); sb.append(", ");
            sb.append("CorrectAnswer: "); sb.append(answerSet.get(i).getQuestion().getCorrectAnswers()); sb.append(", ");
            sb.append("UserAnswer: "); sb.append(answerSet.get(i).getUserAnswers()); sb.append(", ");
        }*/

        StringBuilder sb = new StringBuilder();
        UserExercise userExercise = userExerciseService.getById(userExerciseId);
        List<AnswerSet> answerSet = userExercise.getAnswerSets();
        for (int i=0; i<answerSet.size(); i++){
           sb.append(String.valueOf(answerSet.get(i).check()));
            sb.append(" ,");
        }

        return sb.toString();

    }
}

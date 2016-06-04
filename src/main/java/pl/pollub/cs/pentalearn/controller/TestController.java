package pl.pollub.cs.pentalearn.controller;

import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentalearn.domain.*;
import pl.pollub.cs.pentalearn.service.ChapterService;
import pl.pollub.cs.pentalearn.service.ExerciseService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchExerciseException;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wojciech on 2016-06-04.
 */
@RestController
@RequestMapping("api/exercise/")
public class TestController {
    private final ExerciseService exerciseService;
    private final UserTestService userTestService;
    private final AnswerTestService answerTestService;

    @Inject
    TestController(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    //Create userTest and send Exercise (Test)
    @RequestMapping(value = "{exerciseId}", method = RequestMethod.GET)
    public UserTest StartExerciseById(@PathVariable Long exerciseId) throws NoSuchExerciseException{
        UserTest userTest = new UserTest(exercise);
        userTestService.save(userTest);
        return userTest;
    }

    //id of question is in answerSet
    @RequestMapping(value = "{userTestId}", method = RequestMethod.POST)
    public void addUserAnswer(@PathVariable Long userTestId ,@Valid @RequestBody AnswerSet answerSet){
        answerTestService.addAnswerSetByUserTestId(userTestId, answerSet);
    }

    //zakładam że nie było śmieszka i test rozwiązany do końca
    //zwraca string. nic lepszego nei wymyśliłem
    @RequestMapping(value = "{userTestId}/stop")
    public String  showResults(@PathVariable Long userTestId){
        StringBuilder sb = new StringBuilder();
        UserTest userTest = userTestService.findById(userTestId);
        List<AnswerSet> answerSet = userTest.getAnswerSets();
        for (int i=0; i<answerSet.size(); i++){
            sb.append("QustionId: "); sb.append(answerSet.get(i).getQuestion().getId()); sb.append(", ");
            sb.append("CorrectAnswer: "); sb.append(answerSet.get(i).getQuestion().getCorrectAnswers()); sb.append(", ");
            sb.append("UserAnswer: "); sb.append(answerSet.get(i).getUserAnswers()); sb.append(", ");
        }
        return sb.toString();
    }
}

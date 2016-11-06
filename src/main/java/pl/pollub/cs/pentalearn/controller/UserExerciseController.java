package pl.pollub.cs.pentalearn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentalearn.domain.*;
import pl.pollub.cs.pentalearn.serializer.Views;
import pl.pollub.cs.pentalearn.service.*;
import pl.pollub.cs.pentalearn.service.exception.IncompatibleAnswerSetException;
import pl.pollub.cs.pentalearn.service.exception.InvalidAnswerSetException;
import pl.pollub.cs.pentalearn.service.exception.NoCorrectAnswerSetAssignedToQuestionException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

import javax.inject.Inject;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

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
    private final UserExerciseResultService userExerciseResultService;
    @Inject
    UserExerciseController(ExerciseService exerciseService, UserExerciseService userExerciseService, QuestionService questionService, AnswerSetService answerSetService, UserExerciseResultService userExerciseResultService){
        this.exerciseService = exerciseService;
        this.userExerciseService = userExerciseService;
        this.questionService = questionService;
        this.answerSetService = answerSetService;
        this.userExerciseResultService = userExerciseResultService;
    }

    @RequestMapping(value = "exercises/{exerciseId}/start", method = RequestMethod.GET)
    public UserExercise startExerciseById(@PathVariable Long exerciseId) throws NoSuchObjectException, NoCorrectAnswerSetAssignedToQuestionException, JsonProcessingException {
        Exercise exercise=exerciseService.getById(exerciseId);
        UserExercise userExercise = new UserExercise(exercise);
        userExerciseService.save(userExercise);
        return userExercise;
    }

    @RequestMapping(value = "userExercises/{userExerciseId}", method = RequestMethod.GET)
    public UserExercise getUserExercise(@PathVariable Long userExerciseId) throws NoSuchObjectException, JsonProcessingException {

        return userExerciseService.getById(userExerciseId);
    }

    @RequestMapping(value = "userExercises/{userExerciseId}/{questionId}", method = RequestMethod.POST)
    public void addUserAnswer(@PathVariable Long userExerciseId ,@PathVariable Long questionId ,@Valid @RequestBody AnswerSet answerSet)
            throws NoSuchObjectException, InvalidAnswerSetException, IncompatibleAnswerSetException {
        UserExercise exercise=userExerciseService.getById(userExerciseId);
        Question question=questionService.getById(questionId);

        AnswerSet answerSet1=new AnswerSet(answerSet.getTexts(),answerSet.getAnswers(),answerSet.getMultiSelectAllowed(),question,exercise);

        if(AnswerSet.isAnswerSetsCompatible(answerSet1,question.getCorrectAnswerSet())){
            AnswerSet currentAnswerSet=getAnswerSetForQuestionInUserExercise(exercise,question);
            if(currentAnswerSet==null) answerSetService.save(answerSet1);
            else{
                currentAnswerSet.setTexts(answerSet1.getTexts());
                currentAnswerSet.setAnswers(answerSet1.getAnswers());
                currentAnswerSet.setMultiSelectAllowed(answerSet1.getMultiSelectAllowed());
                currentAnswerSet.setQuestion(answerSet1.getQuestion());
                currentAnswerSet.setUserExercise(answerSet1.getUserExercise());
                answerSetService.save(currentAnswerSet);
            }
        }
        else throw new IncompatibleAnswerSetException();

    }
    private AnswerSet getAnswerSetForQuestionInUserExercise(UserExercise userExercise, Question question){
        for(AnswerSet s :userExercise.getAnswerSets() ){
            if(s.getQuestion().getId()==question.getId()){
                return s;
            }
        }
        return  null;
    }

    @RequestMapping(value = "userExercises/{userExerciseId}/stop",method = RequestMethod.GET)
    public UserExerciseResult stopExercise(@PathVariable Long userExerciseId)  throws NoSuchObjectException {
        //TODO: ADD SOME SPECIAL THINGS(CAN NOT DO IT NOW BECAUSE THE LACK OF SPRING SECURITY
        return getResultFromExercise(userExerciseId);

    }
    @RequestMapping(value = "userExercises/{userExerciseId}/result",method = RequestMethod.GET)
    public UserExerciseResult getResultFromExercise(@PathVariable Long userExerciseId) throws NoSuchObjectException {

        double exerciseMadePercentage;
        double correctAnswersInMadeExercisePercentage;
        double finalExerciseResult;
        double answeredQuestions=0;
        double questionsInExercise;

        double correctAnswerSum=0;


        UserExercise userExercise = userExerciseService.getById(userExerciseId);
        Exercise exercise=userExercise.getExercise();

        questionsInExercise=exercise.getQuestions().size();

        for(AnswerSet set:userExercise.getAnswerSets()){
            answeredQuestions++;
            correctAnswerSum+=AnswerSet.matchLevel(set,set.getQuestion().getCorrectAnswerSet());
        }
        exerciseMadePercentage=((double)answeredQuestions/questionsInExercise)*100;
        correctAnswersInMadeExercisePercentage=((double)correctAnswerSum/answeredQuestions)*100;
        finalExerciseResult=((double)correctAnswerSum/questionsInExercise)*100;

        Date date=new Date();
        Timestamp currentTimestamp=new Timestamp(date.getTime());

        UserExerciseResult result= new  UserExerciseResult(exerciseMadePercentage,correctAnswersInMadeExercisePercentage,
                                    finalExerciseResult,userExercise,currentTimestamp);
        userExerciseResultService.save(result);
        return result;

    }
}

package pl.pollub.cs.pentalearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.pollub.cs.pentalearn.domain.Exercise;
import pl.pollub.cs.pentalearn.service.ExerciseService;

import javax.inject.Inject;

/**
 * Created by Wojciech on 2016-04-24.
 * Strategy: User calls for only ONE Question when 'click' next,
 * controller will return next Question.
 * If there is last question will be check exercise
 * and save result save to user DB //TODO IMPLEMENT
 * also will return result to user //TODO IMPLEMENT
 */
@RestController
@RequestMapping(value = "/api/categories/{categoryId}/courses/{courseId}/chapters/{chapterId}/exercise")
public class ExerciseListController {

    //NARAZIE ŁADUJE CAŁY TEST I TYLKO POKAZUJE -- TO JEST DO ZMIANY,
    // NIE MA TEŻ ZAPISU DO BAZY OSIĄGNIĘĆ UNIKALNEGO UŻYTKOWNIKA
    //TRZEBA DODAĆ QUESTION CONTROLLER
    private ExerciseService exerciseService;

    @Inject
    ExerciseListController(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Exercise showExerciseByChapterId(@PathVariable long chapterId){
        return exerciseService.getExerciseByChapterId(chapterId);
    }

}

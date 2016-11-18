package pl.pollub.cs.pentalearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.domain.Exercise;
import pl.pollub.cs.pentalearn.service.ChapterService;
import pl.pollub.cs.pentalearn.service.ExerciseService;
import pl.pollub.cs.pentalearn.service.exception.ObjectHasNoItemsInTableException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/*
* Strategy: User calls for only ONE Question when 'click' next,
 * controller will return next Question.
 * If there is last question will be check exercise
 * and save result save to user DB //TODO IMPLEMENT
 * also will return result to user //TODO IMPLEMENT
 */

@RestController
@RequestMapping(value = "/api/chapters/{chapterId}/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final ChapterService chapterService;

    @Inject
    ExerciseController(ExerciseService exerciseService, ChapterService chapterService){
        this.exerciseService = exerciseService;
        this.chapterService = chapterService;
    }

    //TODO: think about return one or many exercises
    @RequestMapping(method = RequestMethod.GET)
    public List<Exercise> showExerciseByChapterId(@PathVariable long chapterId)
            throws NoSuchObjectException, ObjectHasNoItemsInTableException {
        return exerciseService.getExercisesByChapterId(chapterId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addExercise(@PathVariable Long chapterId, @Valid @RequestBody Exercise exercise1,
                            HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        Chapter chapter = chapterService.getById(chapterId);
        Exercise exercise = new Exercise(chapter,exercise1.getTitle());
        exerciseService.save(exercise);
    }

    @RequestMapping(value = "/{exerciseId}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateExercise(@PathVariable Long exerciseId,@Valid @RequestBody Exercise exercise1,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        Exercise exercise = exerciseService.getById(exerciseId);
        exercise.setTitle(exercise1.getTitle());
        exerciseService.update(exercise);
    }

    @RequestMapping(value = "/{exerciseId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteChapter(@PathVariable Long exerciseId,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        Exercise exercise = exerciseService.getById(exerciseId);
        exerciseService.delete(exercise);
    }

}

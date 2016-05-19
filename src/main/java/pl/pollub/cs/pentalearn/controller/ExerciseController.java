package pl.pollub.cs.pentalearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.domain.Exercise;
import pl.pollub.cs.pentalearn.domain.createForm.ExerciseCreateForm;
import pl.pollub.cs.pentalearn.service.ChapterService;
import pl.pollub.cs.pentalearn.service.ExerciseService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchChapter;
import pl.pollub.cs.pentalearn.service.exception.NoSuchExercise;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/chapters/{chapterId}/exercise")
public class ExerciseController {


    private final ExerciseService exerciseService;
    private final ChapterService chapterService;

    @Inject
    ExerciseController(ExerciseService exerciseService, ChapterService chapterService){
        this.exerciseService = exerciseService;
        this.chapterService = chapterService;
    }

    //DO POPRAWY BO NIE MOZE WURZUCIC WIECEJ NIZ JEDEN
    @RequestMapping(method = RequestMethod.GET)
    public Exercise showExerciseByChapterId(@PathVariable long chapterId){
        return exerciseService.getExerciseByChapterId(chapterId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addExercise(@PathVariable Long chapterId, @Valid @RequestBody ExerciseCreateForm exerciseCreateForm,
                            HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse) throws NoSuchChapter  {



        Chapter chapter=chapterService.getById(chapterId);
        Exercise exercise=new Exercise(chapter,exerciseCreateForm.getTitle());
        exerciseService.save(exercise);
    }

    @RequestMapping(value = "/{exerciseId}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateExercise(@PathVariable Long exerciseId,@Valid @RequestBody ExerciseCreateForm exerciseCreateForm,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws NoSuchExercise {

        Exercise exercise=exerciseService.getById(exerciseId);
        exercise.setTitle(exerciseCreateForm.getTitle());
        exerciseService.update(exercise);
    }

    @RequestMapping(value = "/{exerciseId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteChapter(@PathVariable Long exerciseId,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws NoSuchExercise {

        Exercise exercise=exerciseService.getById(exerciseId);
        exerciseService.delete(exercise);

    }

}

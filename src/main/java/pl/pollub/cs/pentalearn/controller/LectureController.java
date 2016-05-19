package pl.pollub.cs.pentalearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.domain.Exercise;
import pl.pollub.cs.pentalearn.domain.Lecture;
import pl.pollub.cs.pentalearn.domain.createForm.ExerciseCreateForm;
import pl.pollub.cs.pentalearn.domain.createForm.LectureCreateForm;
import pl.pollub.cs.pentalearn.service.ChapterService;
import pl.pollub.cs.pentalearn.service.LectureService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchChapter;
import pl.pollub.cs.pentalearn.service.exception.NoSuchExercise;
import pl.pollub.cs.pentalearn.service.exception.NoSuchLecture;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by Wojciech on 2016-05-02.
 */
@RestController
@RequestMapping(value = "/api/chapters/{chapterId}/lectures")
public class LectureController {

    private final LectureService lectureService;
    private final ChapterService chapterService;

    @Inject
    public LectureController(LectureService lectureService, ChapterService chapterService){
        this.lectureService = lectureService;
        this.chapterService = chapterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Lecture showLectureByChapterId(@PathVariable Long chapterId){
        return lectureService.getLectureByChapterId(chapterId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addLecture(@PathVariable Long chapterId, @Valid @RequestBody LectureCreateForm lectureCreateForm,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) throws NoSuchChapter {

        Chapter chapter=chapterService.getById(chapterId);
        Lecture lecture=new Lecture(chapter,lectureCreateForm.getContent());
        lectureService.save(lecture);
    }

    @RequestMapping(value = "/{lectureId}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateLecture(@PathVariable Long lectureId,@Valid @RequestBody LectureCreateForm lectureCreateForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) throws  NoSuchLecture {

        Lecture lecture=lectureService.getById(lectureId);
        lecture.setContent(lectureCreateForm.getContent());
        lectureService.update(lecture);
    }

    @RequestMapping(value = "/{lectureId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteLecture(@PathVariable Long lectureId,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws NoSuchLecture {

        Lecture lecture=lectureService.getById(lectureId);
        lectureService.delete(lecture);

    }
}

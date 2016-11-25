package pl.pollub.cs.pentalearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.domain.Lecture;
import pl.pollub.cs.pentalearn.service.ChapterService;
import pl.pollub.cs.pentalearn.service.LectureService;
import pl.pollub.cs.pentalearn.service.exception.ObjectHasNoItemsInTableException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Wojciech on 2016-05-02.
 */
@RestController
@RequestMapping(value = "/api/chapters/{chapterId}/lectures")
public class LectureController {
    private final LectureService lectureService;
    private final ChapterService chapterService;

    @Inject
    public LectureController(LectureService lectureService, ChapterService chapterService) {
        this.lectureService = lectureService;
        this.chapterService = chapterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Lecture> showLecturesByChapterId(@PathVariable Long chapterId) throws ObjectHasNoItemsInTableException, NoSuchObjectException {
        return lectureService.getLecturesByChapterId(chapterId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addLecture(@PathVariable Long chapterId, @Valid @RequestBody Lecture lecture1,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        Chapter chapter = chapterService.getById(chapterId);
        Lecture lecture = new Lecture(chapter, lecture1.getContent());
        lectureService.save(lecture);
    }

    @RequestMapping(value = "/{lectureId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateLecture(@PathVariable Long lectureId, @Valid @RequestBody Lecture lecture1,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        Lecture lecture = lectureService.getById(lectureId);
        lecture.setContent(lecture1.getContent());
        lectureService.update(lecture);
    }

    @RequestMapping(value = "/{lectureId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteLecture(@PathVariable Long lectureId,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        Lecture lecture = lectureService.getById(lectureId);
        lectureService.delete(lecture);
    }
}

package pl.pollub.cs.pentalearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.pollub.cs.pentalearn.domain.Lecture;
import pl.pollub.cs.pentalearn.service.LectureService;

import javax.inject.Inject;

/**
 * Created by Wojciech on 2016-05-02.
 */
@RestController
@RequestMapping(value = "/api/categories/{categoryId}/courses/{courseId}/chapters/{chapterId}/lectures")
public class LectureListController {

    private final LectureService lectureService;

    @Inject
    public LectureListController(LectureService lectureService){
        this.lectureService = lectureService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Lecture showLectureByChapterId(@PathVariable Long chapterId){
        return lectureService.getLectureByChapterId(chapterId);
    }
}

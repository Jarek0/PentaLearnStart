package pl.pollub.cs.pentalearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.domain.Course;
import pl.pollub.cs.pentalearn.service.ChapterService;
import pl.pollub.cs.pentalearn.service.CourseService;
import pl.pollub.cs.pentalearn.service.exception.ObjectHasNoItemsInTableException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Wojciech on 2016-05-14.
 */
@RestController
@RequestMapping(value = "/api/courses/{courseId}/chapters")
public class ChapterController {
    private final ChapterService chapterService;
    private final CourseService courseService;


    @Inject
    ChapterController(ChapterService chapterService, CourseService courseService) {
        this.chapterService = chapterService;
        this.courseService = courseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Chapter> showChaptersByCourseId(@PathVariable Long courseId) throws NoSuchObjectException, ObjectHasNoItemsInTableException {
        return chapterService.getChaptersByCourseId(courseId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addChapter(@PathVariable Long courseId, @Valid @RequestBody Chapter chapter1,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        Course course = courseService.getById(courseId);
        Chapter chapter = new Chapter(chapter1.getName(), chapter1.getDescription(), course);

        chapterService.save(chapter);
    }

    @RequestMapping(value = "/{chapterId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateChapter(@PathVariable Long chapterId, @Valid @RequestBody Chapter chapter1,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        Chapter chapter = chapterService.getById(chapterId);
        chapter.setDescription(chapter1.getDescription());
        chapter.setName(chapter1.getName());
        chapterService.update(chapter);
    }

    @RequestMapping(value = "/{chapterId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteChapter(@PathVariable Long chapterId,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        Chapter chapter = chapterService.getById(chapterId);

        chapterService.delete(chapter);
    }
}

package pl.pollub.cs.pentalearn.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.service.ChapterService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Wojciech on 2016-05-14.
 */
@RestController
@RequestMapping(value = "/api/categories/{categoryId}/courses/{courseId}/chapters")
public class ChapterListController {

    private final ChapterService chapterService;

    @Inject
    ChapterListController(ChapterService chapterService){
        this.chapterService = chapterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Chapter> showChaptersByCourseId(@PathVariable Long courseId){
         return chapterService.getChaptersByCourseId(courseId);
    }

}

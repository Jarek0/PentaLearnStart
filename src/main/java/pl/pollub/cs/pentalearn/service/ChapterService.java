package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.service.exception.NoSuchChapter;
import pl.pollub.cs.pentalearn.service.exception.NoSuchCourse;

import java.util.List;

/**
 * Created by pglg on 12-05-2016.
 */
public interface ChapterService {
    List<Chapter> getList();
    Chapter save(Chapter chapter);
    Chapter update(Chapter chapter);
    void delete(Chapter chapter);
    List<Chapter> getChaptersByCourseId(long courseId) throws NoSuchCourse;
    Chapter getById(Long id) throws NoSuchChapter;

}

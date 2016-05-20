package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.service.exception.NoSuchChapterException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchCourseException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;

import java.util.List;

/**
 * Created by pglg on 12-05-2016.
 */
public interface ChapterService {
    List<Chapter> getList() throws TableIsEmptyException;
    Chapter save(Chapter chapter);
    Chapter update(Chapter chapter);
    void delete(Chapter chapter);
    List<Chapter> getChaptersByCourseId(long courseId) throws NoSuchCourseException;
    Chapter getById(Long id) throws NoSuchChapterException;

}

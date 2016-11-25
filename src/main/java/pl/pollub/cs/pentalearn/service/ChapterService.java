package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.service.exception.ObjectHasNoItemsInTableException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

import java.util.List;

/**
 * Created by pglg on 12-05-2016.
 */
public interface ChapterService {
    List<Chapter> getList() throws TableIsEmptyException;

    Chapter save(Chapter chapter);

    Chapter update(Chapter chapter);

    void delete(Chapter chapter);

    List<Chapter> getChaptersByCourseId(long courseId) throws NoSuchObjectException, ObjectHasNoItemsInTableException;

    Chapter getById(Long id) throws NoSuchObjectException;

}

package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Lecture;
import pl.pollub.cs.pentalearn.service.exception.ObjectHasNoItemsInTableException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

import java.util.List;

/**
 * Created by pglg on 12-05-2016.
 */
public interface LectureService {
    List<Lecture> getList() throws TableIsEmptyException;

    List<Lecture> getLecturesByChapterId(long chapterId) throws ObjectHasNoItemsInTableException, NoSuchObjectException;

    Lecture save(Lecture lecture);

    Lecture update(Lecture lecture);

    void delete(Lecture lecture);

    Lecture getById(Long id) throws NoSuchObjectException;
}

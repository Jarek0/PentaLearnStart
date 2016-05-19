package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Exercise;
import pl.pollub.cs.pentalearn.domain.Lecture;
import pl.pollub.cs.pentalearn.service.exception.NoSuchLecture;

import java.util.List;

/**
 * Created by pglg on 12-05-2016.
 */
public interface LectureService {
    List<Lecture> getList();
    Lecture getLectureByChapterId(long chapterId);
    Lecture save(Lecture lecture);
    Lecture update(Lecture lecture);
    void delete(Lecture lecture);
    Lecture getById(Long id) throws NoSuchLecture;
}

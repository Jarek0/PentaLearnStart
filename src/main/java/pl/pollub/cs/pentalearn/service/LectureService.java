package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Exercise;
import pl.pollub.cs.pentalearn.domain.Lecture;

import java.util.List;

/**
 * Created by pglg on 12-05-2016.
 */
public interface LectureService {
    List<Lecture> getList();
    Lecture getLectureByChapterId(long chapterId);
}

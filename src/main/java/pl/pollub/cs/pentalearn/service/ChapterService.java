package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.Chapter;

import java.util.List;

/**
 * Created by pglg on 12-05-2016.
 */
public interface ChapterService {
    List<Chapter> getList();
    List<Chapter> getChaptersByCourseId(long courseId);
}

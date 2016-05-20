package pl.pollub.cs.pentalearn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentalearn.domain.Lecture;

import java.util.List;

/**
 * Created by pglg on 12-05-2016.
 */
@Repository
public interface LectureRepository extends CrudRepository<Lecture,Long> {
    List<Lecture> getLecturesByChapterId(long chapterId);
}

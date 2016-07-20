package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.pollub.cs.pentalearn.domain.Chapter;
import pl.pollub.cs.pentalearn.domain.Exercise;
import pl.pollub.cs.pentalearn.repository.ChapterRepository;
import pl.pollub.cs.pentalearn.repository.ExerciseRepository;
import pl.pollub.cs.pentalearn.service.ExerciseService;
import pl.pollub.cs.pentalearn.service.exception.ObjectHasNoItemsInTableException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by pglg on 24-04-2016.
 */
@Service
@Validated
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ChapterRepository chapterRepository;

    @Inject
    public ExerciseServiceImpl(final ExerciseRepository exerciseRepository, final ChapterRepository chapterRepository) {
        this.exerciseRepository = exerciseRepository;
        this.chapterRepository = chapterRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> getList() throws TableIsEmptyException {
        List<Exercise> exercises = (List<Exercise>) exerciseRepository.findAll();
        if(exercises.size() == 0)
            throw new TableIsEmptyException("Exercise");
        return exercises;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> getExercisesByChapterId(long chapterId) throws NoSuchObjectException, ObjectHasNoItemsInTableException {
        List<Exercise> exercises;
        exercises = getExercisesIfChapterExist(chapterId);
        CheckIfArrayIsEmpty(exercises, chapterId);
        return exercises;
    }

    private List<Exercise> getExercisesIfChapterExist(long chapterId) throws NoSuchObjectException{
        Chapter chapter = chapterRepository.findOne(chapterId);
        if(chapter == null) throw new NoSuchObjectException(chapterId);
        return chapter.getExercises();
    }

    private void CheckIfArrayIsEmpty(List<Exercise> exercises, long chapterId) throws ObjectHasNoItemsInTableException{
        if(exercises.size() == 0) throw new ObjectHasNoItemsInTableException(chapterId);
    }


    @Override
    @Transactional
    public Exercise save(@NotNull @Valid Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    @Transactional
    public Exercise update(@NotNull @Valid Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    @Transactional
    public void delete(@NotNull Exercise exercise) {
        exerciseRepository.delete(exercise);
    }

    @Override
    @Transactional(readOnly = true)
    public Exercise getById(Long id) throws NoSuchObjectException {
        Exercise exercise=exerciseRepository.findOne(id);
        if(exercise==null)
            throw new NoSuchObjectException(id);
        return exercise;
    }
}

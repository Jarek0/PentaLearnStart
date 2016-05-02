package com.pentalearn.pentalearnstart.database.Course.Chapter.Exercise;

import com.pentalearn.pentalearnstart.model.Course.Chapter.Exercise.Exercise;

import java.util.ArrayList;

/**
 * Mock/Stub Database responsible for exercises
 * * //TODO create real DB - use hibernate
 * Created by Wojciech on 2016-04-30.
 */
public class ExerciseDB {

    private static ArrayList<Exercise> exerciseList = new ArrayList<Exercise>(){{
        add(0, new Exercise(0, 0, "Test1", TaskDB.getTasksByExerciseId(0)));
        add(1, new Exercise(1, 1, "Matematyka - podstawy", TaskDB.getTasksByExerciseId(1)));

        add(2, new Exercise(2,2, "Bardzo wazne zadanie", TaskDB.getTasksByExerciseId(2)));
    }};

    public static Exercise getExerciseByChapterId(int id){
        Exercise returnExercise = null;
        for(Exercise exercise: exerciseList){
            if(exercise.getChapterId() == id) returnExercise = exercise;
        }
        return returnExercise;
    }


}

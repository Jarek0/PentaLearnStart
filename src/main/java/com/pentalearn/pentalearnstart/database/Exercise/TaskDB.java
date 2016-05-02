package com.pentalearn.pentalearnstart.database.Exercise;

import com.pentalearn.pentalearnstart.model.Exercise.Task;

import java.util.ArrayList;

/**
 *  Stub Database responsible for tasks
 * * //TODO create real DB - use hibernate
 * Created by Wojciech on 2016-04-30.
 */
public class TaskDB {
    private static ArrayList<Task> tasks = new ArrayList<Task>(){{
        //test Exercise:
        add(new Task(1, 0, "2 jest poprawne", AnswerDB.getAnswersByTaskId(1)));
        add(new Task(2, 0, "Kto rzadzi?", AnswerDB.getAnswersByTaskId(2)));
        //math Exercise:
        add(new Task(3, 1, "Wzór na pole koła: P=", AnswerDB.getAnswersByTaskId(3)));
        add(new Task(4, 1, "Zaznacz poprawne zdania: ", AnswerDB.getAnswersByTaskId(4)));
        add(new Task(5, 1, "2+2*2 to:", AnswerDB.getAnswersByTaskId(5)));
    }};

    public static ArrayList<Task> getTasksByExerciseId(int exerciseId){
        ArrayList<Task> returnList = new ArrayList<Task>();
        for(Task task: tasks){
            if(task.getExerciseId() == exerciseId) returnList.add(task);
        }
        return returnList;
    }


}

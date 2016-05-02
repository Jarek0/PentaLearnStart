package com.pentalearn.pentalearnstart.database.Course.Chapter.Exercise;

import com.pentalearn.pentalearnstart.model.Course.Chapter.Exercise.Task.Answer.Answer;

import java.util.ArrayList;

/**
 * * Mock/Stub Database responsible for answers
 * * //TODO create real DB - use hibernate
 * Created by Wojciech on 2016-04-30.
 */
public class AnswerDB {
    private static ArrayList<Answer> answerList = new ArrayList<Answer>(){{
        //Exercise - test
        add(new Answer(1, 1, "1", false));
        add(new Answer(2, 1, "2", true));
        add(new Answer(3, 1, "3", false));

        add(new Answer(100, 2, ".Net", false));
        add(new Answer(101, 2, "Java", true));
        add(new Answer(102, 2, "Legia", true));

        //Exercise - math
        add(new Answer(103, 3, "(\u03C0)^2 * r", false));
        add(new Answer(104, 3, "\u03C0 + r", false));
        add(new Answer(105, 3, "\u03C0 * r^2", true));
        add(new Answer(106, 3, "\u03C0 + a*b", false));

        add(new Answer(107, 4, "Każdy kwadrat jest prostokątem", true));
        add(new Answer(108, 4, "Każdy prostokąt jest kwadratem", false));
        add(new Answer(109, 4, "Każde koło jest figurą", true));

        add(new Answer(110, 5, "16", false));
        add(new Answer(111, 5, "8", false));
        add(new Answer(112, 5, "6", true));

        add(new Answer(113, 6, "aaaaa", true));
        add(new Answer(114, 6, "bbbbb", false));
    }};

    public static ArrayList<Answer> getAnswersByTaskId(int taskId){
        ArrayList<Answer> returnList = new ArrayList<Answer>();
        for(Answer answer: answerList){
            if(answer.getTaskId() == taskId) returnList.add(answer);
        }
        return returnList;
    }
}

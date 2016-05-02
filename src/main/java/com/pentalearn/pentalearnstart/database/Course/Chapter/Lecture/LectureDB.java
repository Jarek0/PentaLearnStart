package com.pentalearn.pentalearnstart.database.Course.Chapter.Lecture;

import com.pentalearn.pentalearnstart.model.Course.Chapter.Lecture.Lecture;

import java.util.ArrayList;

/**
 * Stub Database responsible for lecture
 * * //TODO create real DB - use hibernate
 * Created by Wojciech on 2016-05-02.
 */
public class LectureDB {
    //stub DB
    private static ArrayList<Lecture> lectureList = new ArrayList<Lecture>(){{
        add(0, new Lecture( 0, 0, "JAKIS CONTENT"));
        add(1, new Lecture(1, 1, "Kolo - opis, jakies tam jezcze inne rzeczy"));

        add(2, new Lecture(2, 2, "AAA BARDZO WAŻNE INFORMACJE"));
    }};

    public static Lecture getLectureByChapterId(int id){
        Lecture returnLecture = null;
        for(Lecture lecture: lectureList){
            if(lecture.getChapterId() == id) returnLecture = lecture;
        }
        return returnLecture;
    }
}

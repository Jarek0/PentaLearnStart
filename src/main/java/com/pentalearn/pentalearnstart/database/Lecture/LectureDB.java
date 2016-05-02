package com.pentalearn.pentalearnstart.database.Lecture;

import com.pentalearn.pentalearnstart.model.Lecture.Lecture;

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
    }};

    public static Lecture getLectureByCourseId(int id){
        Lecture returnLecture = null;
        for(Lecture lecture: lectureList){
            if(lecture.getCourseId() == id) returnLecture = lecture;
        }
        return returnLecture;
    }
}

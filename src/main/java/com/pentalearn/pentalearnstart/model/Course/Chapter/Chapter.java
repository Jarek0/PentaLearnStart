package com.pentalearn.pentalearnstart.model.Course.Chapter;

/**
 * Chapter class, contains one lecture and one exercise
 * Created by Wojciech on 2016-05-02.
 */
public class Chapter {
    private int id;
    private int courseId;
    private int lectureId;
    private int exerciseId;

    public Chapter(int id, int courseId, int lectureId, int exerciseId){
        this.id = id;
        this.courseId = courseId;
        this.lectureId = lectureId;
        this.exerciseId = exerciseId;
    }

    public int getId() {return id;}

    public int getCourseId() {return courseId;}

    public int getLectureId() {return lectureId;}

    public int getExerciseId() {return exerciseId;}
}

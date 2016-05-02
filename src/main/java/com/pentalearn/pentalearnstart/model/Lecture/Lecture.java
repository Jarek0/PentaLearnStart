package com.pentalearn.pentalearnstart.model.Lecture;

/**
 * This class is responsible for learn before exercise
 * Created by Wojciech on 2016-05-02.
 */
public class Lecture {
    private int id;
    private int courseId;
    private String content;

    public Lecture(int id, int courseId, String content){
        this.id = id;
        this.courseId = courseId;
        this.content = content;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getCourseId() { return courseId;}
    public void setCourseId(int courseId) {this.courseId = courseId;}

    public String getContent() { return content;}
    public void setContent(String content) {this.content = content;}
}

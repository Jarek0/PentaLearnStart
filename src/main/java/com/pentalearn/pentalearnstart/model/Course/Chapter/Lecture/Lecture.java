package com.pentalearn.pentalearnstart.model.Course.Chapter.Lecture;

/**
 * This class is responsible for learn before exercise
 * Created by Wojciech on 2016-05-02.
 */
public class Lecture {
    private int id;
    private int chapterId;
    private String content;

    public Lecture(int id, int chapterId, String content){
        this.id = id;
        this.chapterId = chapterId;
        this.content = content;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getChapterId() { return chapterId;}
    public void setChapterId(int chapterId) {this.chapterId = chapterId;}

    public String getContent() { return content;}
    public void setContent(String content) {this.content = content;}
}

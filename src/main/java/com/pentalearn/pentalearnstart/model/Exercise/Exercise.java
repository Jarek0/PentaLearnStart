package com.pentalearn.pentalearnstart.model.Exercise;

import java.util.ArrayList;

/**
 * Created by Wojciech on 2016-04-24.
 * Main Exercise class responsible for multiply choice test
 * Exercise contains Task, and Task contains Answer
 */
public class Exercise {
    //TODO ID will be great for DataBase,
    private int id;
    private int courseId;
    private String title;
    private ArrayList<Task> tasks;

    public Exercise(){};
    public Exercise(int id, int courseId, String title, ArrayList<Task> tasks){
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.tasks = tasks;
    }

    public int getId() {return id;}
    public int getCourseId() {return courseId;}
    public String getTitle() {return title;}
    public ArrayList<Task> getTasks() {return tasks;}
}

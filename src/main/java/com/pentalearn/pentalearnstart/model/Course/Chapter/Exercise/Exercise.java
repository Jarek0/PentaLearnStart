package com.pentalearn.pentalearnstart.model.Course.Chapter.Exercise;

import com.pentalearn.pentalearnstart.model.Course.Chapter.Exercise.Task.Task;

import java.util.ArrayList;

/**
 * Created by Wojciech on 2016-04-24.
 * Main Exercise class responsible for multiply choice test
 * Exercise contains Task, and Task contains Answer
 */
public class Exercise {
    private int id;
    private int chapterId;
    private String title;
    private ArrayList<Task> tasks;

    public Exercise(){};
    public Exercise(int id, int chapterId, String title, ArrayList<Task> tasks){
        this.id = id;
        this.chapterId = chapterId;
        this.title = title;
        this.tasks = tasks;
    }

    public int getId() {return id;}
    public int getChapterId() {return chapterId;}
    public String getTitle() {return title;}
    public ArrayList<Task> getTasks() {return tasks;}
}

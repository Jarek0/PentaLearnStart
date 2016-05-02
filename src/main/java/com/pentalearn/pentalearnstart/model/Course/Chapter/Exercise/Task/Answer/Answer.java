package com.pentalearn.pentalearnstart.model.Course.Chapter.Exercise.Task.Answer;

/**
 * Created by Wojciech on 2016-04-24.
 * There is Answer class for Task class
 */
public class Answer {
    private int id;
    private int taskId;
    private String text;
    private boolean correct;

    public Answer(int id, int taskId, String text, boolean correct){
        this.id = id;
        this.taskId = taskId;
        this.text = text;
        this.correct = correct;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getTaskId() {return taskId;}
    public void setTaskId(int taskId) {this.taskId = taskId;}

    public String getText() {return text;}
    public void setText(String text) {this.text = text;}

    public boolean getCorrect() {return correct;}
    public void setCorrect(boolean correct) {this.correct = correct;}
}

package com.pentalearn.pentalearnstart.model.Exercise;

/**
 * Created by Wojciech on 2016-04-24.
 * There is Answer class for Task class
 */
public class Answer {
    private int id;
    private String text;
    private boolean correct;

    public Answer(int id, String text, boolean correct){
        this.id = id;
        this.text = text;
        this.correct = correct;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getText() {return text;}
    public void setText(String text) {this.text = text;}

    public boolean getCorrect() {return correct;}
    public void setCorrect(boolean correct) {this.correct = correct;}
}

package com.pentalearn.pentalearnstart.model.Exercise;

import java.util.ArrayList;

/**
 * Created by Wojciech on 2016-04-24.
 * There is Task Class for Exercise class
 */
public class Task {
    private int id;
    private int exerciseId;
    private String question;
    private ArrayList<Answer> answers;

    public Task(int id, int exerciseId, String question, ArrayList<Answer> answers){
        this.id = id;
        this.exerciseId = exerciseId;
        this.question = question;
        this.answers = answers;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getExerciseId() {return exerciseId;}
    public void setExerciseId(int exerciseId) { this.exerciseId = exerciseId; }

    public String getQuestion() {return question;}
    public void setQuestion(String question) {this.question = question;}

    public ArrayList<Answer> getAnswers(){return answers;}
    public void setAnswers(ArrayList<Answer> answers) {this.answers = answers;}
}

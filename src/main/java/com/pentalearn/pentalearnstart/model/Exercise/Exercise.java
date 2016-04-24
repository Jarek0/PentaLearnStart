package com.pentalearn.pentalearnstart.model.Exercise;

import java.util.ArrayList;

/**
 * Created by Wojciech on 2016-04-24.
 * Main Exercise class responsible for multiply choice test
 */
public class Exercise {
    //TODO ID will be great for DataBase,
    private int id;
    private String title;
    private ArrayList<Task> tasks;

    //dummy data, look bellow functions//TODO eventually read all stuff from Database
    public String getTitle(){
        return "Przykładowy test";
    }
    public ArrayList<Task> getTasks(){
        tasks = new ArrayList<Task>();

        tasks.add(new Task(1, "2 jest poprawne", new ArrayList<Answer>(){{
            add(new Answer(1, "1", false));
            add(new Answer(2, "2", true));
            add(new Answer(3, "3", false));
        }}   ));
        tasks.add(new Task(2, "Kto rzadzi?", new ArrayList<Answer>(){{
            add(new Answer(100, ".Net", false));
            add(new Answer(101, "Java", true));
            add(new Answer(102, "Legia", true));
        }}  ));

        return tasks;
    }
}

package com.pentalearn.pentalearnstart.model.Course;

import java.util.ArrayList;

/**
 * Class which is responsible for courses.
 * There are two parts of class:
 * lecture is responsible for learning,
 * if user ends learn, exercise will appear.
 * //TODO add list of courses and corresponding exercises, e.q chapter(?)
 * Created by Wojciech on 2016-04-30.
 */
public class Course {
    private int id;
    private ArrayList<Integer> chapterIds;
    private String name;
    private String description;


    public Course(int id, ArrayList<Integer> chapterIds, String name, String description){
        this.id = id;
        this.chapterIds = chapterIds;
        this.name = name;
        this.description = description;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id; }

    public ArrayList<Integer> getChapterIds() {return chapterIds;}
    public void setChapterId(ArrayList<Integer> chapterIds) {this.chapterIds = chapterIds;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}

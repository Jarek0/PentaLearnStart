package com.pentalearn.pentalearnstart.model.Course;

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
    private int lectureId;
    private int exerciseId;
    private String name;
    private String description;


    public Course(int id, int lectureId, int exerciseId, String name, String description){
        this.id = id;
        this.lectureId = lectureId;
        this.exerciseId = exerciseId;
        this.name = name;
        this.description = description;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id; }

    public int getLectureId() {return lectureId;}
    public void setLectureId(int lectureId) {this.lectureId = lectureId;}

    public int getExerciseId() { return exerciseId;}
    public void setExerciseId(int exerciseId) {this.exerciseId = exerciseId;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}

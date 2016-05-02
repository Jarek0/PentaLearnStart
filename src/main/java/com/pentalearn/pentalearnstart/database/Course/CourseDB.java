package com.pentalearn.pentalearnstart.database.Course;

import com.pentalearn.pentalearnstart.model.Course.Course;

import java.util.ArrayList;

/**
 * Mock/Stub Database responsible for answers
 * * //TODO create real DB - use hibernate
 * Created by Wojciech on 2016-04-30.
 */
public class CourseDB {
    private static ArrayList<Course> courses = new ArrayList<Course>();
    static {
        courses.add(0, new Course(0, 0, 0,"Testowy", "LOREM IPSUM ---------------------!!!----"));
        courses.add(1, new Course(1, 1, 1, "Matematyka", "Podstawy Matematyki"));
    }

    public static ArrayList<Course> getAllCourses() {return courses;}
    public static Course getCourseById(int id) {return courses.get(id);}
}

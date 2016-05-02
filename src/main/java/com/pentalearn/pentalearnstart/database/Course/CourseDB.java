package com.pentalearn.pentalearnstart.database.Course;

import com.pentalearn.pentalearnstart.database.Course.Chapter.ChapterDB;
import com.pentalearn.pentalearnstart.model.Course.Chapter.Chapter;
import com.pentalearn.pentalearnstart.model.Course.Course;

import java.util.ArrayList;

/**
 * Mock/Stub Database responsible for answers
 * * //TODO create real DB - use hibernate
 * Created by Wojciech on 2016-04-30.
 */
public class CourseDB {
    private static ArrayList<Course> coursesList = new ArrayList<Course>();
    static {
        //add stub courses, in ArrayList connected chapters
        coursesList.add(0, new Course(0, new ArrayList<Integer>() {{add(0); add(2);}} ,"Testowy", "LOREM IPSUM ---------------------!!!----"));
        coursesList.add(1, new Course(1, new ArrayList<Integer>() {{add(1);}}, "Matematyka", "Podstawy Matematyki"));
    }

    public static ArrayList<Course> getAllCourses() {return coursesList;}
    public static Course getCourseById(int id) {return coursesList.get(id);}

    /** Get Chapters from Course which is get by ID */
    public static ArrayList<Chapter> getChaptersByCourseId(int courseId){
        ArrayList<Chapter> returnList = new ArrayList<Chapter>();
        Course courseById = getCourseById(courseId);

        for(Integer chapterId: courseById.getChapterIds()) {
            returnList.add(ChapterDB.getChapterById(chapterId));
        }

        return returnList;
    }
}

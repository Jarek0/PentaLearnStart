package com.pentalearn.pentalearnstart.database.Course.Chapter;

import com.pentalearn.pentalearnstart.model.Course.Chapter.Chapter;

import java.util.ArrayList;

/**
 * Created by Wojciech on 2016-05-02.
 */
public class ChapterDB {
    private static ArrayList<Chapter> chapterList = new ArrayList<Chapter>();
    static {
       chapterList.add(0, new Chapter(0, 0, 0, 0));
       chapterList.add(1, new Chapter(1, 1, 1, 1));

        chapterList.add(2, new Chapter(2,0,2,2));
    }

    public static Chapter getChapterById(int chapterId){
        return chapterList.get(chapterId);
    }


}

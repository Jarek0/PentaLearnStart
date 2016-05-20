package pl.pollub.cs.pentalearn.service.exception;

/**
 * Created by pglg on 19-05-2016.
 */
public class NoSuchLectureException extends Exception {
    public NoSuchLectureException(long lectureId) {
        super("There is no lecture of this ID: " + lectureId);
    }
}

package pl.pollub.cs.pentalearn.service.exception;

/**
 * Created by pglg on 19-05-2016.
 */
public class NoSuchChapterException extends Exception {
    public NoSuchChapterException(long chapterId) {
        super("There is no chapter of this ID: " + chapterId);
    }
}

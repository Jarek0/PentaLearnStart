package pl.pollub.cs.pentalearn.service.exception;

/**
 * Created by Wojciech on 2016-07-13.
 */
public class NoSuchObjectException extends Exception {
    public NoSuchObjectException(long objectId){
        super("There is no object of this id: " + objectId);
    }
}

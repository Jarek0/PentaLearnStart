package pl.pollub.cs.pentalearn.service.exception;

/**
 * Created by Wojciech on 2016-07-13.
 */
public class ObjectHasNoItemsInTableException extends Exception {
    public ObjectHasNoItemsInTableException(long objectId) {
        super("Object (id: " + objectId + " ) has no items in array");
    }
}

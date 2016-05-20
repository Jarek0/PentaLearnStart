package pl.pollub.cs.pentalearn.service.exception;

/**
 * Created by Wojciech on 2016-05-21.
 */
public class TableIsEmptyException extends Exception {
    public TableIsEmptyException(String tableName){
        super("Table: " + tableName + " is empty, problem with connection(?) or you've just dropped it");
    }
}

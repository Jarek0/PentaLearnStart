package pl.pollub.cs.pentalearn.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pollub.cs.pentalearn.service.exception.*;

/**
 * Created by pglg on 17-05-2016.
 */
@ControllerAdvice
public class CentralControllerHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchObjectException.class})
    public ResponseEntity<String> handleNoSuchObject(NoSuchObjectException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ObjectHasNoItemsInTableException.class})
    public ResponseEntity<String> handleObjectHasNoItemsInTable(ObjectHasNoItemsInTableException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({TableIsEmptyException.class})
    public ResponseEntity<String> tableIsEmpty(TableIsEmptyException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IncompatibleAnswerSetException.class})
    public ResponseEntity<String> incompatibleAnswerSet(IncompatibleAnswerSetException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }


    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({InvalidAnswerSetException.class})
    public ResponseEntity<String> invalidAnswerSet(InvalidAnswerSetException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({NoCorrectAnswerSetAssignedToQuestionException.class})
    public ResponseEntity<String> questionWithoutCorrectAnswerSet(NoCorrectAnswerSetAssignedToQuestionException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }


}

package pl.pollub.cs.pentalearn.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pollub.cs.pentalearn.service.exception.CategoryAlreadyExistException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchChapter;
import pl.pollub.cs.pentalearn.service.exception.NoSuchCourse;

/**
 * Created by pglg on 17-05-2016.
 */
@ControllerAdvice
public class CentralControllerHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({CategoryAlreadyExistException.class})
    public ResponseEntity<String> handleCategoryAlreadyExist(CategoryAlreadyExistException e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchCourse.class})
    public ResponseEntity<String> handleNoSuchCourse(NoSuchCourse e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchChapter.class})
    public ResponseEntity<String> handleNoSuchCourse(NoSuchChapter e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleNoSuchCourse(Exception e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

}

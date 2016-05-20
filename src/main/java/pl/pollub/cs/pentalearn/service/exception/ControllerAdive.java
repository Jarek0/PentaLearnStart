package pl.pollub.cs.pentalearn.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Wojciech on 2016-05-20.
 */
@ControllerAdvice
public class ControllerAdive {
    @ExceptionHandler({NoSuchCourse.class})
    public ResponseEntity<String> CourseNotExist(NoSuchCourse e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }
}

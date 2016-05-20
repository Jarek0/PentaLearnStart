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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchCourse.class})
    public ResponseEntity<String> handleNoSuchCourse(NoSuchCourse e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchChapter.class})
    public ResponseEntity<String> handleNoSuchChapter(NoSuchChapter e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchAnswer.class})
    public ResponseEntity<String> handleNoSuchAnswer(NoSuchAnswer e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchExercise.class})
    public ResponseEntity<String> handleNoSuchExercise(NoSuchExercise e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchLecture.class})
    public ResponseEntity<String> handleNoSuchLecture(NoSuchLecture e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchQuestion.class})
    public ResponseEntity<String> handleNoSuchQuestion(NoSuchQuestion e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

}

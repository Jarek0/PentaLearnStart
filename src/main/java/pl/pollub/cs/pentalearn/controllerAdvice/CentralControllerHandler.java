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
    @ExceptionHandler({NoSuchCourseException.class})
    public ResponseEntity<String> handleNoSuchCourse(NoSuchCourseException e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchChapterException.class})
    public ResponseEntity<String> handleNoSuchChapter(NoSuchChapterException e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchAnswerException.class})
    public ResponseEntity<String> handleNoSuchAnswer(NoSuchAnswerException e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchExerciseException.class})
    public ResponseEntity<String> handleNoSuchExercise(NoSuchExerciseException e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchLectureException.class})
    public ResponseEntity<String> handleNoSuchLecture(NoSuchLectureException e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchQuestionException.class})
    public ResponseEntity<String> handleNoSuchQuestion(NoSuchQuestionException e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchUserExerciseException.class})
    public ResponseEntity<String> handleNoSuchUserExercise(NoSuchUserExerciseException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({TableIsEmptyException.class})
    public ResponseEntity<String> tableIsEmpty(TableIsEmptyException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


}

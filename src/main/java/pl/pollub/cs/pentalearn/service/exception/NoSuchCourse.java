package pl.pollub.cs.pentalearn.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by pglg on 12-05-2016.
 */
public class NoSuchCourse extends RuntimeException {

    public NoSuchCourse(Long courseId) {
        super("could not find course by id: '" + courseId + "'.");
    }


}

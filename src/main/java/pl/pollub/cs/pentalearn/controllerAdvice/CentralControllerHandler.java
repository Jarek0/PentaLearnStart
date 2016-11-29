package pl.pollub.cs.pentalearn.controllerAdvice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.pollub.cs.pentalearn.service.exception.*;
import pl.pollub.cs.pentalearn.service.validator.ErrorResource;
import pl.pollub.cs.pentalearn.service.validator.FieldErrorResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pglg on 17-05-2016.
 */
@ControllerAdvice
public class CentralControllerHandler extends ResponseEntityExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidRequestException.class)
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
        InvalidRequestException ire = (InvalidRequestException) e;
        List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

        List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            FieldErrorResource fieldErrorResource = new FieldErrorResource();
            fieldErrorResource.setResource(fieldError.getObjectName());
            fieldErrorResource.setField(fieldError.getField());
            fieldErrorResource.setCode(fieldError.getCode());
            fieldErrorResource.setMessage(fieldError.getDefaultMessage());
            fieldErrorResources.add(fieldErrorResource);
        }

        ErrorResource error = new ErrorResource("InvalidRequest", ire.getMessage());
        error.setFieldErrors(fieldErrorResources);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

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

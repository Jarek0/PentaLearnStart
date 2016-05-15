package pl.pollub.cs.pentalearn.service.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(final String message) {
        super(message);
    }

}

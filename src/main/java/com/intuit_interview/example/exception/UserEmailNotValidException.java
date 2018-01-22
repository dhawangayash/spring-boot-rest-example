package com.intuit_interview.example.exception;

/**
 * Created by dhawangayash on 1/21/18.
 */
public class UserEmailNotValidException extends RuntimeException {
    public UserEmailNotValidException() {
        super();
    }

    public UserEmailNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserEmailNotValidException(String message) {
        super(message);
    }

    public UserEmailNotValidException(Throwable cause) {
        super(cause);
    }
}

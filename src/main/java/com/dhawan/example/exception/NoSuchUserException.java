package com.dhawan.example.exception;

/**
 * Created by dhawangayash on 1/31/18.
 */
public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException() {
        super();
    }

    public NoSuchUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchUserException(String message) {
        super(message);
    }

    public NoSuchUserException(Throwable cause) {
        super(cause);
    }

}

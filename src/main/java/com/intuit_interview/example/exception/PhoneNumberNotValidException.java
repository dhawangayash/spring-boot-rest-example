package com.intuit_interview.example.exception;

/**
 * Created by dhawangayash on 1/21/18.
 */
public class PhoneNumberNotValidException extends RuntimeException {
    public PhoneNumberNotValidException() {
        super();
    }

    public PhoneNumberNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneNumberNotValidException(String message) {
        super(message);
    }

    public PhoneNumberNotValidException(Throwable cause) {
        super(cause);
    }
}

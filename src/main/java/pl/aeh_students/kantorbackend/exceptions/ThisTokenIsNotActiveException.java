package pl.aeh_students.kantorbackend.exceptions;

import lombok.Getter;

@Getter
public class ThisTokenIsNotActiveException extends RuntimeException {

    private final String message;

    public ThisTokenIsNotActiveException() {
        message = "Ten token nie jest aktywny";
    }
}

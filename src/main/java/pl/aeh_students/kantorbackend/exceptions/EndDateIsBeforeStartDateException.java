package pl.aeh_students.kantorbackend.exceptions;

import lombok.Getter;

@Getter
public class EndDateIsBeforeStartDateException extends RuntimeException {

    private final String message;

    public EndDateIsBeforeStartDateException() {
        this.message = "Data końcowa nie może być przed datą początkową";
    }
}

package pl.aeh_students.kantorbackend.exceptions;

import lombok.Getter;

@Getter
public class NoAccountWithSuchIdException extends RuntimeException {

    private final String message;

    public NoAccountWithSuchIdException() {
        this.message = "Brak konta o podanym id";
    }
}

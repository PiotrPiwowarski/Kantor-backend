package pl.aeh_students.kantorbackend.exceptions;

import lombok.Getter;

@Getter
public class BothCurrenciesAreTheSameException extends RuntimeException {

    private final String message;

    public BothCurrenciesAreTheSameException() {
        this.message = "Próba zakupu waluty za pomocą tej samej waluty";
    }
}

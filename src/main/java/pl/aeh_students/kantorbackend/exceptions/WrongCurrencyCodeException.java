package pl.aeh_students.kantorbackend.exceptions;

import lombok.Getter;

@Getter
public class WrongCurrencyCodeException extends RuntimeException {

    private final String message;

    public WrongCurrencyCodeException() {
        this.message = "Błędny kod waluty (trzyliterowy kod waluty (standard ISO 4217))";
    }
}

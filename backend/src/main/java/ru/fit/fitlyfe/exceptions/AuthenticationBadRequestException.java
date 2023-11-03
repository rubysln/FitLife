package ru.fit.fitlyfe.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
public class AuthenticationBadRequestException extends RuntimeException{
    private final String message;

    public AuthenticationBadRequestException(String message) {
        this.message = message;
    }
}

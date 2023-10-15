package ru.fit.fitlyfe.exceptions;

import lombok.Getter;

@Getter
public class UserExceptionNotFound extends RuntimeException {
    private final String message;

    public UserExceptionNotFound(String message) {
        this.message = message;
    }
}

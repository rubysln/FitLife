package ru.fit.fitlyfe.exceptions;

import lombok.Getter;

@Getter
public class UserExceptionBadRequest extends RuntimeException {
    private final String message;

    public UserExceptionBadRequest(String message) {
        this.message = message;
    }
}

package ru.fit.fitlyfe.exceptions;

import lombok.Getter;

@Getter
public class ApiError {
    private final String message;
    private final String code;

    public ApiError(String message, String code) {
        this.message = message;
        this.code = code;
    }
}

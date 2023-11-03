package ru.fit.fitlyfe.exceptions;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final ApiError error;

    public ErrorResponse(ApiError error) {
        this.error = error;
    }
}

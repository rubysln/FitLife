package ru.fit.fitlyfe.exceptions;

import lombok.Getter;

@Getter
public class HealthDataBadRequestException extends RuntimeException{
    private final String message;

    public HealthDataBadRequestException(String message) {
        this.message = message;
    }
}

package ru.fit.fitlyfe.exceptions;

import lombok.Getter;

@Getter
public class HealthDataNotFoundException extends RuntimeException{
    private final String message;

    public HealthDataNotFoundException(String message) {
        this.message = message;
    }
}

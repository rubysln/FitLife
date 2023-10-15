package ru.fit.fitlyfe.exceptions;

import lombok.Getter;

@Getter
public class PhysicalActivityDataBadRequestException extends RuntimeException{
    private final String message;

    public PhysicalActivityDataBadRequestException(String message) {
        this.message = message;
    }
}

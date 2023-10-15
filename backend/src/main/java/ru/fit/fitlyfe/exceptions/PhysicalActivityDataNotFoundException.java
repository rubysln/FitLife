package ru.fit.fitlyfe.exceptions;

import lombok.Getter;

@Getter
public class PhysicalActivityDataNotFoundException extends RuntimeException{
    private String message;

    public PhysicalActivityDataNotFoundException(String message) {
        this.message = message;
    }
}

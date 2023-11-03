package ru.fit.fitlyfe.exceptions;


import lombok.Getter;

@Getter
public class RegisterBadRequestException extends RuntimeException{
    private final String message;

    public RegisterBadRequestException(String message) {
        this.message = message;
    }
}

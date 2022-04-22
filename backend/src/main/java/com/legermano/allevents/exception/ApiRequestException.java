package com.legermano.allevents.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiRequestException extends RuntimeException{
    private final HttpStatus status;
    private final String description;

    public ApiRequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.description = null;
    }

    public ApiRequestException(String message, String description, HttpStatus status) {
        super(message);
        this.status = status;
        this.description = description;
    }

    public ApiRequestException(String message, String description, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
        this.description = description;
    }
}

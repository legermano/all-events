package com.legermano.allevents.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiRequestException extends RuntimeException{
    private final HttpStatus status;

    public ApiRequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ApiRequestException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }
}

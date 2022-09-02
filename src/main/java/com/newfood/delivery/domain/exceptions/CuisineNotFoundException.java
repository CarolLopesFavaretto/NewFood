package com.newfood.delivery.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CuisineNotFoundException extends RuntimeException {
    public CuisineNotFoundException(String message) {
        super(message);
    }
}

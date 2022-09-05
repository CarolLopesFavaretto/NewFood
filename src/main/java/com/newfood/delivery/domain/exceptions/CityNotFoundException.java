package com.newfood.delivery.domain.exceptions;

public class CityNotFoundException extends EntityNotFoundException {

    public CityNotFoundException(String message) {
        super(message);
    }
}

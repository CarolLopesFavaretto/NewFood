package com.newfood.delivery.api.exceptions;

public class CityNotFoundException extends EntityNotFoundException {

    public CityNotFoundException(String message) {
        super(message);
    }
}

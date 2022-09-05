package com.newfood.delivery.domain.exceptions;

public class RestaurantNotFoundException extends EntityNotFoundException {
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}

package com.newfood.delivery.api.exceptions;

public class RestaurantNotFoundException extends EntityNotFoundException {
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}

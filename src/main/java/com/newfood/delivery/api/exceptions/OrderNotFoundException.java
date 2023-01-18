package com.newfood.delivery.api.exceptions;

public class OrderNotFoundException extends EntityNotFoundException{


    public OrderNotFoundException(String message) {
        super(message);
    }
}

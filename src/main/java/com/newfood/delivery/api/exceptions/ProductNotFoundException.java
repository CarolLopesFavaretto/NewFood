package com.newfood.delivery.api.exceptions;

public class ProductNotFoundException extends EntityNotFoundException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}

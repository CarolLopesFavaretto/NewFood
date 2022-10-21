package com.newfood.delivery.api.exceptions;

public abstract class EntityNotFoundException extends BusinessException{

    public EntityNotFoundException(String message) {
        super(message);
    }
}

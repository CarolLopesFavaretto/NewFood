package com.newfood.delivery.domain.exceptions;

public abstract class EntityNotFoundException extends BusinessException{

    public EntityNotFoundException(String message) {
        super(message);
    }
}

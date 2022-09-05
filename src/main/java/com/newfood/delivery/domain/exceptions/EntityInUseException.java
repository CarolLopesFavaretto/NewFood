package com.newfood.delivery.domain.exceptions;

public class EntityInUseException extends BusinessException {
    public EntityInUseException(String message) {
        super(message);
    }
}

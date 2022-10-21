package com.newfood.delivery.api.exceptions;

public class StateNotFoundException extends EntityNotFoundException {

    public StateNotFoundException(String message) {
        super(message);
    }
    public StateNotFoundException(Long id){
        this(String.format("Estado %d não encontrado.", id));
    }
}

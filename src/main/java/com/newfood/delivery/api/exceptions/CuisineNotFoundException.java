package com.newfood.delivery.api.exceptions;

public class CuisineNotFoundException extends EntityNotFoundException {
    public CuisineNotFoundException(String message) {
        super(message);
    }

    public CuisineNotFoundException(Long id) {
        this(String.format("Tipo de cozinha %d não encontrado.", id));
    }
}

package com.newfood.delivery.api.exceptions;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de usuário com código %d", id));
    }
}

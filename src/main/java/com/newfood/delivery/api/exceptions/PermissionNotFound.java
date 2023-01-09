package com.newfood.delivery.api.exceptions;

public class PermissionNotFound extends EntityNotFoundException{
    public PermissionNotFound(String message) {
        super(message);
    }

    public PermissionNotFound(Long id) {
        this(String.format("Não existe uma permissão com código %d", id));
    }
}

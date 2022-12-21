package com.newfood.delivery.api.exceptions;

public class GroupNotFoundException extends EntityNotFoundException {

    public GroupNotFoundException(String message) {
        super(message);
    }
}

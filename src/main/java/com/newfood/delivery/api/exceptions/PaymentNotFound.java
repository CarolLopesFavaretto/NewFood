package com.newfood.delivery.api.exceptions;

public class PaymentNotFound extends EntityNotFoundException {

    public PaymentNotFound(String message) {
        super(message);
    }

    public PaymentNotFound(Long id) {
        this(String.format("Não existe pamaneto com o código %d", id));
    }
}

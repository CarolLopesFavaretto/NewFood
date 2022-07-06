package com.newfood.delivery.domain.repository;

import com.newfood.delivery.domain.model.Payment;

import java.util.List;

public interface PaymentRepository {

    List<Payment> list();
    Payment findById( Long id);
    Payment save (Payment payment);
    Payment update(Payment payment);
    void delete(Payment payment);
}

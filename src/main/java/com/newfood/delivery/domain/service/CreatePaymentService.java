package com.newfood.delivery.domain.service;

import com.newfood.delivery.api.exceptions.PaymentNotFound;
import com.newfood.delivery.domain.model.Payment;
import com.newfood.delivery.domain.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreatePaymentService {

    @Autowired
    private PaymentRepository repository;

    @Transactional
    public Payment save(Payment payment) {
        return repository.save(payment);
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new PaymentNotFound(e.getMessage());
        }
    }

    public Payment findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new PaymentNotFound(String.format("O código %d informado não foi encontrado", id)));
    }

}

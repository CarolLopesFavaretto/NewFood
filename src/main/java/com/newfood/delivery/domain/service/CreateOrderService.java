package com.newfood.delivery.domain.service;

import com.newfood.delivery.api.exceptions.OrderNotFoundException;
import com.newfood.delivery.domain.model.Orders;
import com.newfood.delivery.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderService {

    @Autowired
    private OrderRepository repository;

    public Orders findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new OrderNotFoundException(String.format("O código %d informado não foi encontrado", id)));
    }
}

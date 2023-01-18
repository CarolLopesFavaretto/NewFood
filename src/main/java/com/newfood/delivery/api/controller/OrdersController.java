package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.Orders;
import com.newfood.delivery.domain.repository.OrderRepository;
import com.newfood.delivery.domain.service.CreateOrderService;
import com.newfood.delivery.dto.OrdersDTO;
import com.newfood.delivery.dto.response.OrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrdersController {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private CreateOrderService service;

    @Autowired
    private OrdersDTO dto;

    @GetMapping
    public List<OrdersResponse> findAll() {
        List<Orders> all = repository.findAll();
        return dto.toCollection(all);
    }

    @GetMapping("/{id}")
    public OrdersResponse findById(@PathVariable Long id) {
        Orders orders = service.findById(id);
        return dto.toModel(orders);
    }
}

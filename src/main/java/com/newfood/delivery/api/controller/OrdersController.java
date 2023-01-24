package com.newfood.delivery.api.controller;

import com.newfood.delivery.api.exceptions.BusinessException;
import com.newfood.delivery.api.exceptions.EntityNotFoundException;
import com.newfood.delivery.domain.model.Orders;
import com.newfood.delivery.domain.model.User;
import com.newfood.delivery.domain.repository.OrderRepository;
import com.newfood.delivery.domain.service.CreateOrderService;
import com.newfood.delivery.dto.OrdersDTO;
import com.newfood.delivery.dto.request.OrderRequest;
import com.newfood.delivery.dto.response.OrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdersResponse created (@Valid @RequestBody OrderRequest request) {
        try {
            Orders newOrders = dto.toObject(request);

            // TODO pegar usuário autenticado
            newOrders.setUser(new User());
            newOrders.getUser().setId(2L);

            service.emitir(newOrders);

            return dto.toModel(newOrders);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}

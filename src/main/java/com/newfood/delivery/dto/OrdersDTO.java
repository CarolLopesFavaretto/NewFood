package com.newfood.delivery.dto;

import com.newfood.delivery.domain.model.Orders;
import com.newfood.delivery.domain.model.User;
import com.newfood.delivery.dto.request.OrderRequest;
import com.newfood.delivery.dto.response.OrdersResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrdersDTO {

    @Autowired
    private ModelMapper mapper;

    public OrdersResponse toModel(Orders orders) {
        return mapper.map(orders, OrdersResponse.class);
    }

    public Orders toObject(OrderRequest request) {
        return mapper.map(request, Orders.class);
    }

    public void updateObject(OrderRequest request, Orders orders) {
        orders.setUser(new User());
        mapper.map(request, orders);
    }

    public List<OrdersResponse> toCollection(List<Orders> orders) {
        return orders.stream().map(order -> toModel(order))
                .collect(Collectors.toList());
    }
}

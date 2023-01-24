package com.newfood.delivery.domain.service;

import com.newfood.delivery.domain.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderFlow {

    @Autowired
    private CreateOrderService service;

    @Transactional
    public void approved(Long orderId) {
        Orders order = service.findById(orderId);
        order.approved();
    }

    @Transactional
    public void canceled(Long orderId) {
        Orders order = service.findById(orderId);
        order.canceled();
    }

    @Transactional
    public void delivered(Long orderId) {
        Orders order = service.findById(orderId);
        order.delivered();
    }
}

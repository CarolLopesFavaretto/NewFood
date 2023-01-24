package com.newfood.delivery.domain.model;

import com.newfood.delivery.domain.service.OrderFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders/{orderId}")
public class OrderFlowController {

    @Autowired
    private OrderFlow service;

    @PutMapping("/approved")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void approved(@PathVariable Long orderId) {
        service.approved(orderId);
    }

    @PutMapping("/canceled")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void canceled(@PathVariable Long orderId) {
        service.canceled(orderId);
    }

    @PutMapping("/delivered")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delivered(@PathVariable Long orderId) {
        service.delivered(orderId);
    }
}

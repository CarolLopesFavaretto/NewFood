package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.service.CreateRestaurantService;
import com.newfood.delivery.dto.PaymentDTO;
import com.newfood.delivery.dto.response.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant/{restaurantId}/payment")
public class RestaurantPaymentController {

    @Autowired
    private CreateRestaurantService service;

    @Autowired
    private PaymentDTO dto;

    @GetMapping
    public List<PaymentResponse> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = service.findById(restaurantId);
        return dto.toCollectionModel(restaurant.getPayment());
    }

    @PutMapping("{paymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatedPayment(@PathVariable Long restaurantId, @PathVariable Long paymentId) {
        service.addPayment(restaurantId, paymentId);
    }

    @DeleteMapping("{paymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removedPayment(@PathVariable Long restaurantId, @PathVariable Long paymentId) {
        service.removePayment(restaurantId, paymentId);
    }


}

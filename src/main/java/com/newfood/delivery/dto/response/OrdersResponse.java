package com.newfood.delivery.dto.response;

import com.newfood.delivery.domain.model.Address;
import com.newfood.delivery.domain.model.StatusOrders;
import com.newfood.delivery.dto.request.ItemRequest;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrdersResponse {

    private Long id;
    private BigDecimal subtotal;
    private BigDecimal feeShipping;
    private BigDecimal amount;
    private Address address;
    private StatusOrders status;
    private LocalDateTime createdAt;
    private LocalDateTime dateConfirmation;
    private LocalDateTime dateCancellation;
    private LocalDateTime dateDelivery;
    private PaymentResponse payment;
    private RestaurantResponse restaurant;
    private UserResponse user;
    private List<ItemRequest> items;
}

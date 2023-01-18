package com.newfood.delivery.dto.request;

import com.newfood.delivery.domain.model.Address;
import com.newfood.delivery.domain.model.StatusOrders;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private BigDecimal subtotal;
    private BigDecimal feeShipping;
    private BigDecimal amount;
    private Address address;
    private StatusOrders status;
    //    private LocalDateTime createdAt;
//    private LocalDateTime dateConfirmation;
//    private LocalDateTime dateCancellation;
//    private LocalDateTime dateDelivery;
//    private PaymentRequest payment;
//    private RestaurantRequest restaurant;
//    private UserRequest user;
    private List<ItemRequest> items;

}

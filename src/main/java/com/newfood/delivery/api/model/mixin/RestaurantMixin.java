package com.newfood.delivery.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.newfood.delivery.domain.model.Address;
import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.model.Payment;
import com.newfood.delivery.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public abstract class RestaurantMixin {

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private Cuisine cuisine;

    @JsonIgnore
    private Address address;

    @JsonIgnore
    private List<Payment> payment = new ArrayList<>();

    @JsonIgnore
    private List<Product> products = new ArrayList<>();
}

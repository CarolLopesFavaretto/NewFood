package com.newfood.delivery.domain.repository;

import com.newfood.delivery.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQuerys {

    List<Restaurant> find (String name, BigDecimal shippingBegin, BigDecimal shippingFinal);
}


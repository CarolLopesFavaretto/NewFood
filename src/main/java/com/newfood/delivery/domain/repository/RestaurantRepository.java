package com.newfood.delivery.domain.repository;

import com.newfood.delivery.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> list();
    Restaurant findById(Long id);
    Restaurant save(Restaurant restaurant);
    void delete(Long id);
}

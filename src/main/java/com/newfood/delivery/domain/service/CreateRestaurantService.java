package com.newfood.delivery.domain.service;

import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateRestaurantService {

    @Autowired
    private RestaurantRepository repository;

    public Restaurant save( Restaurant restaurant){
        return repository.save(restaurant);
    }
}

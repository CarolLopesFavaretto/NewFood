package com.newfood.delivery.domain.service;

import com.newfood.delivery.domain.exceptions.CuisineNotFoundException;
import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.repository.CuisineRepository;
import com.newfood.delivery.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateRestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private CuisineRepository cuisineRepository;

    public Restaurant save(Restaurant restaurant) {
        Long id = restaurant.getCuisine().getId();
        Cuisine cuisine = cuisineRepository.findById(id);

        if (cuisine == null) {
            throw new CuisineNotFoundException(String.format("O código %d informado não foi encontrado", id));

        }
        return repository.save(restaurant);

    }
}

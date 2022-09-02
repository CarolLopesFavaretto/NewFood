package com.newfood.delivery.domain.service;

import com.newfood.delivery.domain.exceptions.CuisineNotFoundException;
import com.newfood.delivery.domain.exceptions.RestaurantNotFoundException;
import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.repository.CuisineRepository;
import com.newfood.delivery.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CreateRestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CreateCuisineService cuisineService;

    public Restaurant save(Restaurant restaurant) {
        Long id = restaurant.getCuisine().getId();
        Cuisine cuisine = cuisineService.findById(id);
        return repository.save(restaurant);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RestaurantNotFoundException(
                    String.format("Restaurante %d não encontrado.", id));
        }
    }

    public Restaurant findById(Long id){
        return repository.findById(id).orElseThrow(() ->
                new RestaurantNotFoundException(String.format("O código %d informado não foi encontrado", id)));
    }
}

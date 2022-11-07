package com.newfood.delivery.domain.service;

import com.newfood.delivery.api.exceptions.RestaurantNotFoundException;
import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateRestaurantService {

    @Autowired
    private RestaurantRepository repository;
    @Autowired
    private CreateCuisineService cuisineService;

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Long id = restaurant.getCuisine().getId();
        Cuisine cuisine = cuisineService.findById(id);
        restaurant.setCuisine(cuisine);
        return repository.save(restaurant);
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new RestaurantNotFoundException(
                    String.format("Restaurante %d não encontrado.", id));
        }
    }

    @Transactional
    public void active(Long id) {
        Restaurant restaurant = findById(id);
        restaurant.active();
    }

    @Transactional
    public void inactive(Long id) {
        Restaurant restaurant = findById(id);
        restaurant.inactive();
    }

    public Restaurant findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RestaurantNotFoundException(String.format("O código %d informado não foi encontrado", id)));
    }
}

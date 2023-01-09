package com.newfood.delivery.domain.service;

import com.newfood.delivery.api.exceptions.RestaurantNotFoundException;
import com.newfood.delivery.domain.model.City;
import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.model.Payment;
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

    @Autowired
    private CreateCityService cityService;

    @Autowired
    private CreatePaymentService paymentService;

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Long cuisineId = restaurant.getCuisine().getId();
        Long cityId = restaurant.getAddress().getCity().getId();

        Cuisine cuisine = cuisineService.findById(cuisineId);
        City city = cityService.findById(cityId);

        restaurant.setCuisine(cuisine);
        restaurant.getAddress().setCity(city);

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
    public void removePayment(Long restaurantId, Long paymentId) {
        Restaurant restaurant = findById(restaurantId);
        Payment payment = paymentService.findById(paymentId);
        restaurant.getPayment().remove(payment);
    }

    @Transactional
    public void addPayment(Long restaurantId, Long paymentId) {
        Restaurant restaurant = findById(restaurantId);
        Payment payment = paymentService.findById(paymentId);
        restaurant.getPayment().add(payment);
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

    @Transactional
    public void open(Long id) {
        Restaurant restaurant = findById(id);
        restaurant.open();
    }

    @Transactional
    public void close(Long id) {
        Restaurant restaurant = findById(id);
        restaurant.close();
    }

    public Restaurant findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RestaurantNotFoundException(String.format("O código %d informado não foi encontrado", id)));
    }
}

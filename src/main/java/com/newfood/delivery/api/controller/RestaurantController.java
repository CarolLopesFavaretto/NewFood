package com.newfood.delivery.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newfood.delivery.api.exceptions.BusinessException;
import com.newfood.delivery.api.exceptions.CuisineNotFoundException;
import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.repository.RestaurantRepository;
import com.newfood.delivery.domain.service.CreateRestaurantService;
import com.newfood.delivery.dto.RestaurantDTO;
import com.newfood.delivery.dto.response.RestaurantResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.newfood.delivery.infra.spec.RestaurantRepositorySpcs.namesEquals;
import static com.newfood.delivery.infra.spec.RestaurantRepositorySpcs.shippingZero;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private CreateRestaurantService service;

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private RestaurantDTO dto;

    @GetMapping
    public List<RestaurantResponse> list() {
        List<Restaurant> restaurants = repository.findAll();
        return dto.toCollectionModel(restaurants);
    }

    @GetMapping("/for-cuisine")
    public List<Restaurant> getByName(@RequestParam String name, Long cuisineId) {
        return repository.getByNameAndCuisine(name, cuisineId);
    }

    @GetMapping("/for-shipping")
    public List<Restaurant> getByShipping(@RequestParam String name, BigDecimal shippingBegin, BigDecimal shippingFinal) {
        return repository.find(name, shippingBegin, shippingFinal);
    }

    @GetMapping("/for-shipping-zero")
    public List<Restaurant> getByShippingZero(@RequestParam String name) {
        return repository.findAll(shippingZero().and(namesEquals(name)));
    }

    @GetMapping("/{id}")
    public RestaurantResponse findById(@PathVariable Long id) {
        Restaurant restaurant = service.findById(id);
        return dto.toModel(restaurant);
    }

    @PostMapping
    public Restaurant created(@RequestBody @Valid Restaurant restaurant) {
        try {
            return service.save(restaurant);
        } catch (CuisineNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Restaurant updated(@RequestBody @Valid Restaurant restaurant, @PathVariable Long id) {
        Restaurant newRestaurant = service.findById(id);
        BeanUtils.copyProperties(restaurant, newRestaurant, "id");
        try {
            return service.save(newRestaurant);
        } catch (CuisineNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public Restaurant updatedByCamps(@PathVariable Long id, @RequestBody Map<String, Object> camps) {
        Restaurant newRestaurant = service.findById(id);
        merge(camps, newRestaurant);
        return updated(newRestaurant, id);
    }

    private void merge(Map<String, Object> origin, Restaurant destiny) {
        ObjectMapper mapper = new ObjectMapper();
        Restaurant restaurantOrigin = mapper.convertValue(origin, Restaurant.class);

        origin.forEach((propertsName, valueName) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, propertsName);
            field.setAccessible(true);
            Object newValue = ReflectionUtils.getField(field, restaurantOrigin);

            ReflectionUtils.setField(field, destiny, newValue);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

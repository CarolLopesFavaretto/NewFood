package com.newfood.delivery.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newfood.delivery.domain.exceptions.BusinessException;
import com.newfood.delivery.domain.exceptions.CuisineNotFoundException;
import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.repository.RestaurantRepository;
import com.newfood.delivery.domain.service.CreateRestaurantService;
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

    @GetMapping
    public List<Restaurant> list() {
        return repository.findAll();
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
    public Restaurant findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Restaurant created(@RequestBody @Valid Restaurant restaurant) {
        try {
            return service.save(restaurant);
        }catch (CuisineNotFoundException e){
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Restaurant updated(@RequestBody @Valid Restaurant restaurant, @PathVariable Long id) {
        Restaurant newRestaurant = service.findById(id);
        BeanUtils.copyProperties(restaurant, newRestaurant, "id");
        try {
            return service.save(newRestaurant);
        }catch (CuisineNotFoundException e){
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

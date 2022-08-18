package com.newfood.delivery.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newfood.delivery.domain.exceptions.CuisineNotFoundException;
import com.newfood.delivery.domain.exceptions.RestaurantNotFoundException;
import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.repository.RestaurantRepository;
import com.newfood.delivery.domain.service.CreateRestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        return repository.getByNameAndCuisine(name,cuisineId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        Optional<Restaurant> restaurant = repository.findById(id);
        if (restaurant.isPresent()) {
            return ResponseEntity.ok().body(restaurant.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> created(@RequestBody Restaurant restaurant) {
        try {
            restaurant = service.save(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
        } catch (CuisineNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updated(@RequestBody Restaurant restaurant, @PathVariable Long id) {
        try {
            Restaurant newRestaurant = repository.findById(id).orElse(null);
            BeanUtils.copyProperties(restaurant, newRestaurant, "id");
            service.save(newRestaurant);
            return ResponseEntity.status(HttpStatus.OK).body(newRestaurant);
        } catch (CuisineNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatedByCamps(@PathVariable Long id, @RequestBody Map<String, Object> camps) {
        Restaurant newRestaurant = repository.findById(id).orElse(null);

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
    public ResponseEntity<Restaurant> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

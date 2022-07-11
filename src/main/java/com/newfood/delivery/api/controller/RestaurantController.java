package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.exceptions.CuisineNotFoundException;
import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.repository.RestaurantRepository;
import com.newfood.delivery.domain.service.CreateRestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private CreateRestaurantService service;

    @Autowired
    private RestaurantRepository repository;

    @GetMapping
    public List<Restaurant> list() {
        return repository.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        Restaurant restaurant = repository.findById(id);
        if (restaurant != null) {
            return ResponseEntity.ok().body(restaurant);
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
            Restaurant newRestaurant = repository.findById(id);

            if (newRestaurant != null) {
                BeanUtils.copyProperties(restaurant, newRestaurant, "id");
                service.save(newRestaurant);
                return ResponseEntity.status(HttpStatus.OK).body(newRestaurant);
            }
            return ResponseEntity.notFound().build();
        } catch (CuisineNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

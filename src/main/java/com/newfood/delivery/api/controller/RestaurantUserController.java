package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.service.CreateRestaurantService;
import com.newfood.delivery.dto.UserDTO;
import com.newfood.delivery.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant/{restaurantId}/user")
public class RestaurantUserController {

    @Autowired
    private CreateRestaurantService service;

    @Autowired
    private UserDTO dto;

    @GetMapping
    List<UserResponse> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = service.findById(restaurantId);
        return dto.toCollectionModel(restaurant.getUsers());
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addUser(@PathVariable Long restaurantId, @PathVariable Long userId) {
        service.addUser(restaurantId, userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable Long restaurantId, @PathVariable Long userId) {
        service.removeUser(restaurantId, userId);
    }
}

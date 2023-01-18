package com.newfood.delivery.api.controller;

import com.newfood.delivery.api.exceptions.BusinessException;
import com.newfood.delivery.api.exceptions.CuisineNotFoundException;
import com.newfood.delivery.api.exceptions.RestaurantNotFoundException;
import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.repository.RestaurantRepository;
import com.newfood.delivery.domain.service.CreateRestaurantService;
import com.newfood.delivery.dto.RestaurantDTO;
import com.newfood.delivery.dto.request.RestaurantRequest;
import com.newfood.delivery.dto.response.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

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
    public RestaurantResponse created(@RequestBody @Valid RestaurantRequest request) {
        try {
            Restaurant restaurant = dto.toObject(request);
            return dto.toModel(service.save(restaurant));
        } catch (CuisineNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestaurantResponse updated(@RequestBody @Valid RestaurantRequest restaurant, @PathVariable Long id) {
        try {
            Restaurant newRestaurant = service.findById(id);
            dto.updateToObject(restaurant, newRestaurant);
            return dto.toModel(service.save(newRestaurant));
        } catch (CuisineNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void active(@PathVariable Long id) {
        service.active(id);
    }

    @PutMapping("/{id}/open")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void open(@PathVariable Long id) {
        service.open(id);
    }

    @PutMapping("/{id}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Long id) {
        service.close(id);
    }

    @PutMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activeList(@RequestBody List<Long> restaurantIds) {
        try {
            service.activeList(restaurantIds);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactiveList(@RequestBody List<Long> restaurantIds) {
        try {
            service.inactiveList(restaurantIds);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactive(@PathVariable Long id) {
        service.inactive(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

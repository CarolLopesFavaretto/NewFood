package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.Product;
import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.repository.ProductRepository;
import com.newfood.delivery.domain.service.CreateProductService;
import com.newfood.delivery.domain.service.CreateRestaurantService;
import com.newfood.delivery.dto.ProductDTO;
import com.newfood.delivery.dto.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant/{restaurantId}/product")
public class RestaurantProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CreateProductService service;

    @Autowired
    private CreateRestaurantService restaurantService;

    @Autowired
    private ProductDTO dto;

    @GetMapping
    public List<ProductResponse> findByRestaurant(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        List<Product> products = productRepository.findByRestaurant(restaurant);
        return dto.toCollectionModel(products);
    }
}

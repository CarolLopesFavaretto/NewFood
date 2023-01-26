package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.Product;
import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.repository.ProductRepository;
import com.newfood.delivery.domain.service.CreateProductService;
import com.newfood.delivery.domain.service.CreateRestaurantService;
import com.newfood.delivery.dto.ProductDTO;
import com.newfood.delivery.dto.request.ProductRequest;
import com.newfood.delivery.dto.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<ProductResponse> findByRestaurant(@PathVariable Long restaurantId, @RequestParam(required = false) boolean findInactive) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        List<Product> products;
        if (findInactive) {
            products = productRepository.findByRestaurant(restaurant);
        } else{
            products = productRepository.findByActiveRestaurant(restaurant);
        }
        return dto.toCollectionModel(products);
    }

    @GetMapping("/{productId}")
    public ProductResponse findById(@PathVariable Long restaurantId, @PathVariable Long productId) {
        Product product = service.findById(restaurantId, productId);
        return dto.toModel(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse created(@PathVariable Long restaurantId, @Valid @RequestBody ProductRequest request) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        Product product = dto.toObject(request);
        product.setRestaurant(restaurant);
        return dto.toModel(productRepository.save(product));
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProductResponse updated(@PathVariable Long restaurantId, @PathVariable Long productId, @Valid @RequestBody ProductRequest request) {
        Product newProduct = service.findById(restaurantId, productId);
        dto.updatedToObject(request, newProduct);
        newProduct = service.save(newProduct);
        return dto.toModel(newProduct);
    }
}

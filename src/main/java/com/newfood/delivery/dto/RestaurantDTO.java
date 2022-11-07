package com.newfood.delivery.dto;

import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.dto.request.RestaurantRequest;
import com.newfood.delivery.dto.response.RestaurantResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantDTO {

    @Autowired
    private ModelMapper mapper;

    public Restaurant toObject(RestaurantRequest request) {
        return mapper.map(request, Restaurant.class);
    }
    public RestaurantResponse toModel(Restaurant restaurant) {
        return mapper.map(restaurant, RestaurantResponse.class);
    }

    public List<RestaurantResponse> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> toModel(restaurant))
                .collect(Collectors.toList());
    }

    public void updateToObject(RestaurantRequest request, Restaurant restaurant) {
        restaurant.setCuisine(new Cuisine());
        mapper.map(request, restaurant);
    }
}

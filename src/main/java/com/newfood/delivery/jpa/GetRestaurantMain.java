package com.newfood.delivery.jpa;

import com.newfood.delivery.DeliveryApplication;
import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.repository.CuisineRepository;
import com.newfood.delivery.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class GetRestaurantMain {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository createdRestaurant = context.getBean(RestaurantRepository.class);

        List<Restaurant> restaurants = createdRestaurant.list();

        for (Restaurant allRestaurant : restaurants) {
            System.out.println(allRestaurant.getCuisine().getName());
        }
    }
}

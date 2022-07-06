package com.newfood.delivery.jpa;

import com.newfood.delivery.DeliveryApplication;
import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.repository.CuisineRepository;
import com.newfood.delivery.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class AddRestaurantMain {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository createdRestaurant = context.getBean(RestaurantRepository.class);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("American Dog");
        restaurant.setShipping(BigDecimal.valueOf(30.70));

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setId(2L);
        restaurant1.setName("Hai Hai");
        restaurant1.setShipping(BigDecimal.valueOf(30));

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setId(3L);
        restaurant2.setName(" Casa do Norte");
        restaurant2.setShipping(BigDecimal.valueOf(30.50));

        createdRestaurant.save(restaurant);
        createdRestaurant.save(restaurant1);
        createdRestaurant.save(restaurant2);
    }
}

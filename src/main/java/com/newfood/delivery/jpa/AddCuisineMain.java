package com.newfood.delivery.jpa;

import com.newfood.delivery.DeliveryApplication;
import com.newfood.delivery.domain.model.Cuisine;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class AddCuisineMain {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CreateCuisine createdCuisine = context.getBean(CreateCuisine.class);

        Cuisine cuisine = new Cuisine();
        cuisine.setId(3L);
        cuisine.setName("americana");

        Cuisine cuisine1 = new Cuisine();
        cuisine1.setId(4L);
        cuisine1.setName("japonesa");

        Cuisine cuisine2 = new Cuisine();
        cuisine2.setId(5L);
        cuisine2.setName(" baiana");

        createdCuisine.save(cuisine);
        createdCuisine.save(cuisine1);
        createdCuisine.save(cuisine2);
    }
}

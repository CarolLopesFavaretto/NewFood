package com.newfood.delivery.jpa;

import com.newfood.delivery.DeliveryApplication;
import com.newfood.delivery.domain.model.Cuisine;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class DeleteCuisineMain {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CreateCuisine createdCuisine = context.getBean(CreateCuisine.class);

        Cuisine cuisine = new Cuisine();
        cuisine.setId(1L);

        createdCuisine.delete(cuisine);

    }
}
package com.newfood.delivery.infra.spec;

import com.newfood.delivery.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantRepositorySpcs {

    public static Specification<Restaurant> namesEquals (String name){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%"));
    }

    public static Specification<Restaurant> shippingZero(){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("shipping"), BigDecimal.ZERO);
    }
}

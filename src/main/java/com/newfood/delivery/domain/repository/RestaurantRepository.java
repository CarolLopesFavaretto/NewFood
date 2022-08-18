package com.newfood.delivery.domain.repository;

import com.newfood.delivery.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryQuerys {

     @Query("from Restaurant where name like %:name% and cuisine_id = :cuisineId")
     List<Restaurant> getByNameAndCuisine(String name, Long cuisineId);

}

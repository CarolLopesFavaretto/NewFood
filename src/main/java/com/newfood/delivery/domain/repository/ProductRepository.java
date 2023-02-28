package com.newfood.delivery.domain.repository;

import com.newfood.delivery.domain.model.Photo;
import com.newfood.delivery.domain.model.Product;
import com.newfood.delivery.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , ProductRepositoryQueys{

    @Query("from Product where restaurant.id = :restaurant and id = :product")
    Optional<Product> findById(@Param("restaurant") Long restaurantId,
                               @Param("product") Long productId);

    List<Product> findByRestaurant(Restaurant restaurant);

    @Query("from Product p where p.active = true and p.restaurant = :restaurant ")
    List<Product> findByActiveRestaurant(Restaurant restaurant);

    @Query("select f from Photo f join f.product p where p.restaurant.id = :restaurantId and f.product =:productId")
    Optional<Photo> findPhotoById (Long restaurantId, Long productId);
}

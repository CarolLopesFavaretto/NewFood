package com.newfood.delivery.domain.repository;

import com.newfood.delivery.domain.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long> {

    List<Cuisine> findListByName (String name);

    Optional<Cuisine> findByName (String name);
}

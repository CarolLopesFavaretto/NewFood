package com.newfood.delivery.domain.repository;

import com.newfood.delivery.domain.model.Cuisine;

import java.util.List;

public interface CuisineRepository {

    List<Cuisine> list();
    Cuisine findById(Long id);
    Cuisine save (Cuisine cuisine);
    void delete(Cuisine cuisine);
}

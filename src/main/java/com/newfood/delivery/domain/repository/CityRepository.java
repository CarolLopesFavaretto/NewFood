package com.newfood.delivery.domain.repository;

import com.newfood.delivery.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> list();
    City findById( Long id);
    City save( City city);
    void delete (City city);
}

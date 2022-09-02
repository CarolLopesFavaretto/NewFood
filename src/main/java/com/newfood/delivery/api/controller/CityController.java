package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.City;
import com.newfood.delivery.domain.repository.CityRepository;
import com.newfood.delivery.domain.service.CreateCityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityRepository repository;

    @Autowired
    private CreateCityService service;


    @GetMapping
    public List<City> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public City findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public City created(@RequestBody City city) {
        return service.save(city);
    }

    @PutMapping("/{id}")
    public City updated(@RequestBody City city, @PathVariable Long id) {
        City newCity = service.findById(id);
        BeanUtils.copyProperties(city, newCity, "id");
        return service.save(newCity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

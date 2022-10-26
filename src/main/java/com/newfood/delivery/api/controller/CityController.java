package com.newfood.delivery.api.controller;

import com.newfood.delivery.api.exceptions.BusinessException;
import com.newfood.delivery.api.exceptions.StateNotFoundException;
import com.newfood.delivery.domain.model.City;
import com.newfood.delivery.domain.repository.CityRepository;
import com.newfood.delivery.domain.service.CreateCityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public City created(@RequestBody @Valid City city) {
        try {
            return service.save(city);
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public City updated(@RequestBody @Valid City city, @PathVariable Long id) {
        City newCity = service.findById(id);
        BeanUtils.copyProperties(city, newCity, "id");
        try {
            return service.save(newCity);
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

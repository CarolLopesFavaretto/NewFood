package com.newfood.delivery.api.controller;

import com.newfood.delivery.api.exceptions.BusinessException;
import com.newfood.delivery.api.exceptions.StateNotFoundException;
import com.newfood.delivery.domain.model.City;
import com.newfood.delivery.domain.repository.CityRepository;
import com.newfood.delivery.domain.service.CreateCityService;
import com.newfood.delivery.dto.CityDTO;
import com.newfood.delivery.dto.request.CityRequest;
import com.newfood.delivery.dto.response.CityResponse;
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

    @Autowired
    private CityDTO dto;

    @GetMapping
    public List<CityResponse> list() {
        List<City> cities = repository.findAll();
        return dto.toCollectionModel(cities);
    }

    @GetMapping("/{id}")
    public CityResponse findById(@PathVariable Long id) {
        City city = service.findById(id);
        return dto.toModel(city);
    }

    @PostMapping
    public CityResponse created(@RequestBody @Valid CityRequest request) {
        try {
            City city = dto.toObject(request);
            return dto.toModel(service.save(city));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public CityResponse updated(@RequestBody @Valid CityRequest request, @PathVariable Long id) {
        try {
            City newCity = service.findById(id);
            dto.updateToObject(request, newCity);
            return dto.toModel(service.save(newCity));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

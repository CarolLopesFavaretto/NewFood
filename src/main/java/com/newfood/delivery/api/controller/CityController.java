package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.exceptions.CityNotFoundException;
import com.newfood.delivery.domain.exceptions.EntityInUseException;
import com.newfood.delivery.domain.model.City;
import com.newfood.delivery.domain.repository.CityRepository;
import com.newfood.delivery.domain.service.CreateCityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return repository.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {
        City city = repository.findById(id);
        if (city != null) {
            return ResponseEntity.ok().body(city);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> created(@RequestBody City city) {
        try {
            service.save(city);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (CityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updated(@RequestBody City city, @PathVariable Long id) {
        City newCity = repository.findById(id);

        if (newCity != null) {
            BeanUtils.copyProperties(city, newCity, "id");
            service.save(newCity);
            return ResponseEntity.ok().body(newCity);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete( @PathVariable Long id){
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }catch (CityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}

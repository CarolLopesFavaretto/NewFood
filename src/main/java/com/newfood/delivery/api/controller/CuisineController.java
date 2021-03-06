package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.exceptions.CuisineNotFoundException;
import com.newfood.delivery.domain.exceptions.EntityInUseException;
import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.repository.CuisineRepository;
import com.newfood.delivery.domain.service.CreateCuisineService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuisine")
public class CuisineController {

    @Autowired
    private CuisineRepository repository;

    @Autowired
    private CreateCuisineService service;

    @GetMapping
    public List<Cuisine> list() {
        return repository.findAll();
    }

    @GetMapping("/all-name")
    public List<Cuisine> findByAllName(@RequestParam String name){
        return repository.findByAllName(name);
    }

    @GetMapping("/name")
    public Optional<Cuisine> findByName(@RequestParam String name){
        return repository.findByName(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuisine> findById(@PathVariable Long id) {
        Optional<Cuisine> cuisine = repository.findById(id);
        if (cuisine.isPresent()) {
            return ResponseEntity.ok(cuisine.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cuisine> save(Cuisine cuisine) {
        try {
            service.save(cuisine);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (CuisineNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuisine> updated(@PathVariable Long id, @RequestBody Cuisine cuisine) {
        Cuisine newCuisine = repository.findById(id).orElse(null);
        try {
            BeanUtils.copyProperties(cuisine, newCuisine, "id"); //igual a: -> newCuisine.setName(cuisine.getName());
            service.save(newCuisine);
            return ResponseEntity.ok(newCuisine);
        } catch (CuisineNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cuisine> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (CuisineNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}

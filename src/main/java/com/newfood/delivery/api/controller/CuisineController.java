package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.repository.CuisineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuisine")
public class CuisineController {

    @Autowired
    private CuisineRepository repository;

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<Cuisine> list() {
        return repository.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuisine> findById(@PathVariable Long id) {
        Cuisine cuisine = repository.findById(id);
        if (cuisine != null) {
            return ResponseEntity.ok(cuisine);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuisine save(Cuisine cuisine) {
        return repository.save(cuisine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuisine> updated(@PathVariable Long id, @RequestBody Cuisine cuisine) {
        Cuisine newCuisine = repository.findById(id);

        BeanUtils.copyProperties(cuisine, newCuisine, "id"); //igual a: -> newCuisine.setName(cuisine.getName());
        repository.save(newCuisine);
        return ResponseEntity.ok(newCuisine);
    }
}

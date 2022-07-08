package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.exceptions.CuisineNotFoundException;
import com.newfood.delivery.domain.exceptions.EntityInUseException;
import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.repository.CuisineRepository;
import com.newfood.delivery.domain.service.CreateCuisineService;
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

    @Autowired
    private CreateCuisineService service;

    @GetMapping
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
        return service.save(cuisine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuisine> updated(@PathVariable Long id, @RequestBody Cuisine cuisine) {
        Cuisine newCuisine = repository.findById(id);
        if (newCuisine != null) {
            BeanUtils.copyProperties(cuisine, newCuisine, "id"); //igual a: -> newCuisine.setName(cuisine.getName());
            service.save(newCuisine);
            return ResponseEntity.ok(newCuisine);
        }
        return ResponseEntity.notFound().build();
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

package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.repository.CuisineRepository;
import com.newfood.delivery.domain.service.CreateCuisineService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<Cuisine> findByAllName(@RequestParam String name) {
        return repository.findListByName(name);
    }

    @GetMapping("/name")
    public Optional<Cuisine> findByName(@RequestParam String name) {
        return repository.findByName(name);
    }

    @GetMapping("/{id}")
    public Cuisine findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Cuisine save(@RequestBody @Valid Cuisine cuisine) {
        return service.add(cuisine);
    }

    @PutMapping("/{id}")
    public Cuisine updated(@PathVariable Long id, @RequestBody @Valid Cuisine cuisine) {
        Cuisine newCuisine = service.findById(id);
        BeanUtils.copyProperties(cuisine, newCuisine, "id"); //igual a: -> newCuisine.setName(cuisine.getName());
        return service.add(newCuisine);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.repository.CuisineRepository;
import com.newfood.delivery.domain.service.CreateCuisineService;
import com.newfood.delivery.dto.CuisineDTO;
import com.newfood.delivery.dto.request.CuisineRequest;
import com.newfood.delivery.dto.response.CuisineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private CuisineDTO dto;

    @GetMapping
    public Page<CuisineResponse> list(Pageable pageable) {
        Page<Cuisine> cuisines = repository.findAll(pageable);
        List<CuisineResponse> list = dto.toCollectionModel(cuisines.getContent());
        Page<CuisineResponse> page = new PageImpl<>(list, pageable, cuisines.getTotalElements());
        return page;
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
    public CuisineResponse findById(@PathVariable Long id) {
        Cuisine cuisine = service.findById(id);
        return dto.toModel(service.findById(id));
    }

    @PostMapping
    public CuisineResponse save(@RequestBody @Valid CuisineRequest request) {
        Cuisine cuisine = dto.toObject(request);
        return dto.toModel(service.add(cuisine));
    }

    @PutMapping("/{id}")
    public CuisineResponse updated(@PathVariable Long id, @RequestBody @Valid CuisineRequest request) {
        Cuisine newCuisine = service.findById(id);
        dto.updateToObject(request, newCuisine);
        return dto.toModel(service.add(newCuisine));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

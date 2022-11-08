package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.State;
import com.newfood.delivery.domain.repository.StateRepository;
import com.newfood.delivery.domain.service.CreateStateService;
import com.newfood.delivery.dto.StateDTO;
import com.newfood.delivery.dto.request.StateRequest;
import com.newfood.delivery.dto.response.StateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateRepository repository;

    @Autowired
    private CreateStateService service;

    @Autowired
    private StateDTO dto;

    @GetMapping
    public List<StateResponse> list() {
        List<State> state = repository.findAll();
        return dto.toCollectionModel(state);
    }

    @GetMapping("/{id}")
    public StateResponse findById(@PathVariable Long id) {
        State state = service.findById(id);
        return dto.toModel(state);
    }

    @PostMapping
    public StateResponse created(@RequestBody @Valid StateRequest request) {
        State state = dto.toObject(request);
        return dto.toModel(service.save(state));
    }

    @PutMapping("/{id}")
    public StateResponse updated(@RequestBody @Valid StateRequest request, @PathVariable Long id) {
        State newState = service.findById(id);
        dto.updateToObject(request, newState);
        return dto.toModel(service.save(newState));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

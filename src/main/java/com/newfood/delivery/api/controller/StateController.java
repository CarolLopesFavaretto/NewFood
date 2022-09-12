package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.State;
import com.newfood.delivery.domain.repository.StateRepository;
import com.newfood.delivery.domain.service.CreateStateService;
import org.springframework.beans.BeanUtils;
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

    @GetMapping
    public List<State> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public State findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public State created(@RequestBody @Valid State state) {
        return service.save(state);
    }

    @PutMapping("/{id}")
    public State updated(@RequestBody @Valid State state, @PathVariable Long id) {
        State newState = service.findById(id);
        BeanUtils.copyProperties(state, newState, "id");
        return service.save(newState);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

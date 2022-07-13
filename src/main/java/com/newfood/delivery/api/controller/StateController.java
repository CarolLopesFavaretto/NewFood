package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.exceptions.StateNotFoundException;
import com.newfood.delivery.domain.model.State;
import com.newfood.delivery.domain.repository.StateRepository;
import com.newfood.delivery.domain.service.CreateStateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<State> findById(@PathVariable Long id) {

        Optional<State> state = repository.findById(id);
        if (state.isPresent()) {
            return ResponseEntity.ok(state.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> created(@RequestBody State state) {
        try {
            service.save(state);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (StateNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updated(@RequestBody State state, @PathVariable Long id) {
        try {
            State newState = repository.findById(id).orElse(null);

            BeanUtils.copyProperties(state, newState, "id");
            service.save(newState);
            return ResponseEntity.status(HttpStatus.OK).body(newState);

        } catch (StateNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (StateNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

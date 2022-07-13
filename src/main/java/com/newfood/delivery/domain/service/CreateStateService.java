package com.newfood.delivery.domain.service;

import com.newfood.delivery.domain.exceptions.StateNotFoundException;
import com.newfood.delivery.domain.model.State;
import com.newfood.delivery.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

@Service
public class CreateStateService {

    @Autowired
    private StateRepository repository;

    public State save(State state) {
        Long id = state.getId();

        if (state == null) {
            throw new StateNotFoundException(String.format("O código %d não foi encontrado", id));
        }
        return repository.save(state);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new StateNotFoundException(
                    String.format("Estado %d não encontrado.", id));
        }
    }
}

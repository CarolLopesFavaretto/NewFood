package com.newfood.delivery.domain.service;

import com.newfood.delivery.api.exceptions.StateNotFoundException;
import com.newfood.delivery.domain.model.State;
import com.newfood.delivery.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateStateService {

    @Autowired
    private StateRepository repository;

    @Transactional
    public State save(State state) {
        return repository.save(state);
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new StateNotFoundException(
                    String.format("Estado %d não encontrado.", id));
        }
    }

    public State findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new StateNotFoundException(id));
    }
}

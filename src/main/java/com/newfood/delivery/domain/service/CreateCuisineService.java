package com.newfood.delivery.domain.service;

import com.newfood.delivery.api.exceptions.CuisineNotFoundException;
import com.newfood.delivery.api.exceptions.EntityInUseException;
import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateCuisineService {

    @Autowired
    private CuisineRepository repository;

    @Transactional
    public Cuisine add(Cuisine cuisine) {
        return repository.save(cuisine);
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Tipo de cozinha %d não pode ser deletado, pois está em uso.", id));

        } catch (EmptyResultDataAccessException e) {
            throw new CuisineNotFoundException(id);
        }
    }

    public Cuisine findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new CuisineNotFoundException(id));
    }
}

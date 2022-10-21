package com.newfood.delivery.domain.service;

import com.newfood.delivery.api.exceptions.CityNotFoundException;
import com.newfood.delivery.domain.model.City;
import com.newfood.delivery.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateCityService {

    @Autowired
    private CityRepository repository;

    @Autowired
    private CreateStateService stateService;

    @Transactional
    public City save(City city) {
        Long id = city.getState().getId();
        stateService.findById(id);
        return repository.save(city);
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CityNotFoundException(
                    String.format("A cidade cujo código %d não foi encontrado.", id));
        }
    }

    public City findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new CityNotFoundException(String.format("A cidade cujo código %d não foi encontrado.", id)));
    }
}

package com.newfood.delivery.domain.service;

import com.newfood.delivery.domain.exceptions.CityNotFoundException;
import com.newfood.delivery.domain.model.City;
import com.newfood.delivery.domain.model.State;
import com.newfood.delivery.domain.repository.CityRepository;
import com.newfood.delivery.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CreateCityService {

    @Autowired
    private CityRepository repository;

    @Autowired
    private StateRepository stateRepository;

    public City save(City city) {
        Long id = city.getState().getId();
        State state = stateRepository.findById(id).orElseThrow(() ->
                new CityNotFoundException(String.format("O cidade cujo código %d não foi encontrada", id)));

        return repository.save(city);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CityNotFoundException(
                    String.format("A cidade cujo código %d não foi encontrado.", id));
        }
    }
}

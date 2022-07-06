package com.newfood.delivery.infrastructure.repository;

import com.newfood.delivery.domain.model.City;
import com.newfood.delivery.domain.repository.CityRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CityRepositoryImp implements CityRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> list() {
        return manager.createQuery("from City", City.class).getResultList();
    }

    @Override
    public City findById(Long id) {
        return manager.find(City.class, id);
    }

    @Override
    @Transactional
    public City save(City city) {
        return manager.merge(city);
    }

    @Override
    @Transactional
    public void delete(City city) {
        city = findById(city.getId());
        manager.remove(city);
    }
}

package com.newfood.delivery.infrastructure.repository;

import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.repository.CuisineRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CuisineRepositoryImp implements CuisineRepository {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Cuisine> list() {
        return manager.createQuery("from Cuisine", Cuisine.class).getResultList();
    }

    @Override
    public Cuisine findById(Long id) {
        return manager.find(Cuisine.class, id);
    }

    @Transactional
    @Override
    public Cuisine save(Cuisine cuisine) {
        return manager.merge(cuisine);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Cuisine cuisine = findById(id);
        if (cuisine == null) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cuisine);
    }
}

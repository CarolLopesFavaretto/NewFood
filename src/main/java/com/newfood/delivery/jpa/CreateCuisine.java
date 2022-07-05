package com.newfood.delivery.jpa;

import com.newfood.delivery.domain.model.Cuisine;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CreateCuisine {

    @PersistenceContext
    private EntityManager manager;

    public List<Cuisine> list() {
        return manager.createQuery("from Cuisine", Cuisine.class).getResultList();
    }

    public Cuisine findById(Long id){
       return manager.find(Cuisine.class, id);
    }

    @Transactional
    public Cuisine save(Cuisine cuisine) {
        return manager.merge(cuisine);
    }

    @Transactional
    public void delete(Cuisine cuisine){
        cuisine = findById(cuisine.getId());
        manager.remove(cuisine);
    }
}

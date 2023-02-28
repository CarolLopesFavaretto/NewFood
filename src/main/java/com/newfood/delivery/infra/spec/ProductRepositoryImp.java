package com.newfood.delivery.infra.spec;

import com.newfood.delivery.domain.model.Photo;
import com.newfood.delivery.domain.repository.ProductRepositoryQueys;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProductRepositoryImp implements ProductRepositoryQueys {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public Photo save(Photo photo) {
        return manager.merge(photo);
    }

    @Override
    public void delete(Photo photo) {
        manager.remove(photo);
    }
}

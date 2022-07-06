package com.newfood.delivery.infrastructure.repository;

import com.newfood.delivery.domain.model.Permission;
import com.newfood.delivery.domain.repository.PermissionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PermissionRepositoryImp implements PermissionRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permission> list() {
        return manager.createQuery("from Permission", Permission.class).getResultList();
    }

    @Override
    public Permission findById(Long id) {
        return manager.find(Permission.class, id);
    }

    @Override
    @Transactional
    public Permission save(Permission permission) {
        return manager.merge(permission);
    }

    @Override
    @Transactional
    public void delete(Permission permission) {
    permission = findById(permission.getId());
    manager.remove(permission);
    }
}

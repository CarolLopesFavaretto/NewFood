package com.newfood.delivery.domain.service;

import com.newfood.delivery.api.exceptions.PermissionNotFound;
import com.newfood.delivery.domain.model.Permission;
import com.newfood.delivery.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePermissionService {

    @Autowired
    private PermissionRepository repository;

    public Permission findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new PermissionNotFound(id));
    }
}

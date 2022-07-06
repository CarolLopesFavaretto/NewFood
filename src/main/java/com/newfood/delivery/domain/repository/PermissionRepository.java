package com.newfood.delivery.domain.repository;

import com.newfood.delivery.domain.model.Permission;

import java.util.List;

public interface PermissionRepository {

    List<Permission> list();
    Permission findById( Long id);
    Permission save (Permission permission);
    void delete (Permission permission);
}

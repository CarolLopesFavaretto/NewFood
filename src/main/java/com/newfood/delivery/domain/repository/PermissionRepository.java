package com.newfood.delivery.domain.repository;

import com.newfood.delivery.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}

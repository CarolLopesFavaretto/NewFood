package com.newfood.delivery.dto;

import com.newfood.delivery.domain.model.Permission;
import com.newfood.delivery.dto.response.PermissionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionDTO {

    @Autowired
    private ModelMapper mapper;

    public PermissionResponse toModel(Permission permission) {
        return mapper.map(permission, PermissionResponse.class);
    }

    public List<PermissionResponse> toCollection(Collection<Permission> permissions) {
        return permissions.stream()
                .map(permission -> toModel(permission))
                .collect(Collectors.toList());
    }
}

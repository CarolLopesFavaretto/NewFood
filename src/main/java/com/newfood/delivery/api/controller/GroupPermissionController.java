package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.Group;
import com.newfood.delivery.domain.service.CreateGroupService;
import com.newfood.delivery.dto.PermissionDTO;
import com.newfood.delivery.dto.response.PermissionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group/{groupId}/permission")
public class GroupPermissionController {

    @Autowired
    private PermissionDTO dto;

    @Autowired
    private CreateGroupService groupService;

    @GetMapping
    List<PermissionResponse> listPermissions (@PathVariable Long groupId){
        Group group = groupService.findById(groupId);
        return dto.toCollection(group.getPermissions());
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPermission (@PathVariable Long groupId, @PathVariable Long permissionId){
        groupService.addPermission(groupId, permissionId);
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePermission (@PathVariable Long groupId, @PathVariable Long permissionId){
        groupService.removePermission(groupId,permissionId);
    }
}

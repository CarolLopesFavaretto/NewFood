package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.User;
import com.newfood.delivery.domain.service.CreateUserService;
import com.newfood.delivery.dto.GroupDTO;
import com.newfood.delivery.dto.response.GroupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/{userId}/group")
public class UserGroupController {

    @Autowired
    private CreateUserService service;

    @Autowired
    private GroupDTO dto;

    @GetMapping
    public List<GroupResponse> groupList(@PathVariable Long userId) {
        User user = service.findById(userId);
        return dto.toCollectionModel(user.getGroups());
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        service.addGroup(userId, groupId);
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        service.removeGroup(userId, groupId);
    }
}

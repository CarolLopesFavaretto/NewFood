package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.Group;
import com.newfood.delivery.domain.repository.GroupRepository;
import com.newfood.delivery.domain.service.CreateGroupService;
import com.newfood.delivery.dto.GroupDTO;
import com.newfood.delivery.dto.request.GroupRequest;
import com.newfood.delivery.dto.response.GroupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupRepository repository;

    @Autowired
    private CreateGroupService service;

    @Autowired
    private GroupDTO dto;


    @GetMapping
    public List<GroupResponse> findAll() {
        List<Group> groups = repository.findAll();
        return dto.toCollectionModel(groups);
    }

    @GetMapping("/{id}")
    public GroupResponse findById(@PathVariable Long id) {
        Group group = service.findById(id);
        return dto.toModel(group);
    }

    @PostMapping
    public GroupResponse created(@RequestBody @Valid GroupRequest request) {
        Group group = dto.toObject(request);
        return dto.toModel(service.save(group));
    }

    @PutMapping("/{id}")
    public GroupResponse updated(@RequestBody @Valid GroupRequest request, @PathVariable Long id) {
        Group newGroup = service.findById(id);
        dto.updatedToObject(request, newGroup);
        return dto.toModel(service.save(newGroup));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

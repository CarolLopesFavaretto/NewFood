package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.User;
import com.newfood.delivery.domain.repository.UserRepository;
import com.newfood.delivery.domain.service.CreateUserService;
import com.newfood.delivery.dto.UserDTO;
import com.newfood.delivery.dto.request.UserPasswordRequest;
import com.newfood.delivery.dto.request.UserRequest;
import com.newfood.delivery.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CreateUserService service;

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserDTO dto;

    @GetMapping
    public List<UserResponse> findAll() {
        List<User> users = repository.findAll();
        return dto.toCollectionModel(users);
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        User user = service.findById(id);
        return dto.toModel(user);
    }

    @PostMapping
    public UserResponse created(@RequestBody @Valid UserRequest request) {
        User user = dto.toObject(request);
        return dto.toModel(service.save(user));
    }

    @PutMapping("/{id}")
    public UserResponse updated(@RequestBody @Valid UserRequest request, @PathVariable Long id) {
        User newUser = service.findById(id);
        dto.updatedToObject(request, newUser);
        return dto.toModel(service.save(newUser));
    }

    @PutMapping("/{id}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatedPassword(@RequestBody @Valid UserPasswordRequest request, @PathVariable Long id) {
        service.updatedPassword(id, request.getCurrentPassword(), request.getNewPassword());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

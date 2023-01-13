package com.newfood.delivery.domain.service;

import com.newfood.delivery.api.exceptions.BusinessException;
import com.newfood.delivery.api.exceptions.UserNotFoundException;
import com.newfood.delivery.domain.model.Group;
import com.newfood.delivery.domain.model.User;
import com.newfood.delivery.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CreateUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CreateGroupService groupService;

    @Transactional
    public User save(User user) {

        Optional<User> existingUser = repository.findByEmail(user.getEmail());

        if (existingUser.isPresent() && !existingUser.get().equals(user)) {
            throw new BusinessException(String.format("Já existe um usuário cadastrado com email %s", user.getEmail()));
        }
        return repository.save(user);
    }

    @Transactional
    public void updatedPassword(Long id, String currentPassword, String newPassword) {
        User user = findById(id);

        if (user.unequalPassword(currentPassword)) {
            throw new BusinessException("Senha atual informada não coincide com a senha do usuário.");
        }
        user.setPassword(newPassword);
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(String.format("Usuario %d não encontrado.", id));
        }
    }

    @Transactional
    public void addGroup(Long userId, Long groupId){
        Group group = groupService.findById(groupId);
        User user = findById(userId);
        user.getGroups().add(group);
    }

    @Transactional
    public void removeGroup (Long userId, Long groupId){
        User user = findById(userId);
        Group group = groupService.findById(groupId);
        user.getGroups().remove(group);
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new UserNotFoundException(String.format("O código %d informado não foi encontrado", id)));
    }
}

package com.newfood.delivery.domain.service;

import com.newfood.delivery.api.exceptions.BusinessException;
import com.newfood.delivery.api.exceptions.UserNotFoundException;
import com.newfood.delivery.domain.model.User;
import com.newfood.delivery.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public User save(User user) {
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

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new UserNotFoundException(String.format("O código %d informado não foi encontrado", id)));
    }
}

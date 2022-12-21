package com.newfood.delivery.domain.service;

import com.newfood.delivery.api.exceptions.GroupNotFoundException;
import com.newfood.delivery.domain.model.Group;
import com.newfood.delivery.domain.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateGroupService {

    @Autowired
    private GroupRepository repository;

    @Transactional
    public Group save(Group group) {
        return repository.save(group);
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new GroupNotFoundException(
                    String.format("Grupo %d não encontrado.", id));
        }
    }

    public Group findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new GroupNotFoundException(String.format("O código %d informado não foi encontrado", id)));
    }
}

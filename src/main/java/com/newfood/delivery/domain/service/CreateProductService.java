package com.newfood.delivery.domain.service;

import com.newfood.delivery.api.exceptions.ProductNotFoundException;
import com.newfood.delivery.domain.model.Product;
import com.newfood.delivery.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(
                String.format("O código %d informado não foi encontrado", id)));
    }
}

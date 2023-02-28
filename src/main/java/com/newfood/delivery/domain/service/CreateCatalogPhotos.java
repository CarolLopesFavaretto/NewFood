package com.newfood.delivery.domain.service;

import com.newfood.delivery.domain.model.Photo;
import com.newfood.delivery.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class CreateCatalogPhotos {

    @Autowired
    private ProductRepository repository;

    @Transactional
    public Photo save(Photo photo) {
        Long restaurantId = photo.getProduct().getRestaurant().getId();
        Long productId = photo.getProduct().getId();
        Optional<Photo> existingPhoto = repository.findPhotoById(restaurantId, productId);
        if (existingPhoto.isPresent()) {
            repository.delete(existingPhoto.get());
        }
        return repository.save(photo);
    }
}

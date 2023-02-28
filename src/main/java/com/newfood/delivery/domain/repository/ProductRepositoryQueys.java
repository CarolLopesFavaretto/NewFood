package com.newfood.delivery.domain.repository;

import com.newfood.delivery.domain.model.Photo;

public interface ProductRepositoryQueys {

        Photo save (Photo photo);

        void delete (Photo photo);
}

package com.newfood.delivery.dto;

import com.newfood.delivery.domain.model.Photo;
import com.newfood.delivery.dto.response.PhotoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhotoDTO {

    @Autowired
    private ModelMapper mapper;

//    public Product toObject(ProductRequest request) {
//        return mapper.map(request, Product.class);
//    }

    public PhotoResponse toModel(Photo photo) {
        return mapper.map(photo, PhotoResponse.class);
    }

//    public List<ProductResponse> toCollectionModel(List<Product> products) {
//        return products.stream().map(product -> toModel(product)).collect(Collectors.toList());
//    }
//
//    public void updatedToObject(ProductRequest request, Product product) {
//        mapper.map(request, product);
//    }
}

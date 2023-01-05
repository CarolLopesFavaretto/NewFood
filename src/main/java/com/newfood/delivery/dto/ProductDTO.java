package com.newfood.delivery.dto;

import com.newfood.delivery.domain.model.Product;
import com.newfood.delivery.dto.request.ProductRequest;
import com.newfood.delivery.dto.response.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDTO {

    @Autowired
    private ModelMapper mapper;

    public Product toObject(ProductRequest request) {
        return mapper.map(request, Product.class);
    }

    public ProductResponse toModel(Product product) {
        return mapper.map(product, ProductResponse.class);
    }

    public List<ProductResponse> toCollectionModel(List<Product> products) {
        return products.stream().map(product -> toModel(product)).collect(Collectors.toList());
    }

    public void updatedToObject(ProductRequest request, Product product) {
        mapper.map(request, product);
    }
}

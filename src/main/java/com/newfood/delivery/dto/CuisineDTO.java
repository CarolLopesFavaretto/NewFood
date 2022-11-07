package com.newfood.delivery.dto;

import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.dto.request.CuisineRequest;
import com.newfood.delivery.dto.response.CuisineResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CuisineDTO {

    @Autowired
    private ModelMapper mapper;

    public Cuisine toObject(CuisineRequest request) {
        return mapper.map(request, Cuisine.class);
    }

    public CuisineResponse toModel(Cuisine cuisine) {
        return mapper.map(cuisine, CuisineResponse.class);
    }

    public List<CuisineResponse> toCollectionModel(List<Cuisine> cuisines) {
        return cuisines.stream()
                .map(cuisine -> toModel(cuisine))
                .collect(Collectors.toList());
    }

    public void updateToObject(CuisineRequest request, Cuisine cuisine) {
        mapper.map(request, cuisine);
    }
}

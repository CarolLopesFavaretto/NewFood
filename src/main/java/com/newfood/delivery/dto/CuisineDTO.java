package com.newfood.delivery.dto;

import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.dto.request.CuisineRequest;
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
        return mapper.map(CuisineRequest.class, Cuisine.class);
    }

    public CuisineRequest toModel(Cuisine cuisine) {
        return mapper.map(cuisine, CuisineRequest.class);
    }

    public List<CuisineRequest> toCollectionModel(List<Cuisine> cuisines) {
        return cuisines.stream()
                .map(cuisine -> toModel(cuisine))
                .collect(Collectors.toList());
    }

    public void updateToObject(CuisineRequest request, Cuisine cuisine) {
        mapper.map(request, cuisine);
    }
}

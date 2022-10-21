package com.newfood.delivery.dto;

import com.newfood.delivery.domain.model.City;
import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.model.State;
import com.newfood.delivery.dto.request.CityRequest;
import com.newfood.delivery.dto.request.CuisineRequest;
import com.newfood.delivery.dto.request.StateRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityDTO {

    @Autowired
    private ModelMapper mapper;

    public City toObject (CityRequest request){
        return mapper.map(CityRequest.class, City.class);
    }

    public CityRequest toModel (City city){
        return mapper.map(City.class, CityRequest.class);
    }

    public List<CityRequest> toCollectionModel(List<City> cities) {
        return cities.stream()
                .map(city -> toModel(city))
                .collect(Collectors.toList());
    }

    public void updateToObject(CityRequest request, City city) {
        city.setState(new State());
        mapper.map(request, city);
    }

}

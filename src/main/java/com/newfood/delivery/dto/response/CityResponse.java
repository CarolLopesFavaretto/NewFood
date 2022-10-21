package com.newfood.delivery.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponse {

    private Long id;
    private String name;
    private StateResponse state;
}

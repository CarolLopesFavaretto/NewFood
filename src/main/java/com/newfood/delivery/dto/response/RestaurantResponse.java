package com.newfood.delivery.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantResponse {

    private Long id;
    private String name;
    private BigDecimal shipping;
    private CuisineResponse cuisine;
    private Boolean active;
}

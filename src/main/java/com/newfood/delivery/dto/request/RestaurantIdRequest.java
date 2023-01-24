package com.newfood.delivery.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class RestaurantIdRequest {

    @NotNull
    private Long id;
}

package com.newfood.delivery.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class ItemRequest {

    private Long productId;
    @NotNull
    @PositiveOrZero
    private Integer quantity;
    private String observation;
}

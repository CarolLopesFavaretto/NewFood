package com.newfood.delivery.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemRequest {

    private BigDecimal value;
    private BigDecimal total;
    private Integer quantity;
    private String observation;
}

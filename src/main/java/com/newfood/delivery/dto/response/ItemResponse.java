package com.newfood.delivery.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemResponse {

    private Long id;
    private String name;
    private BigDecimal value;
    private BigDecimal total;
    private Integer quantity;
    private String observation;
}

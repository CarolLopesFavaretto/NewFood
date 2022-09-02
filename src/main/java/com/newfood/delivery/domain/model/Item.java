package com.newfood.delivery.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Item {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal value;
    private BigDecimal total;
    private Integer quantity;
    private String observation;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;
}

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

    public void calculatedPriceTotal() {
        BigDecimal precoUnitario = this.getValue();
        Integer quantidade = this.getQuantity();

        if (precoUnitario == null) {
            precoUnitario = BigDecimal.ZERO;
        }

        if (quantidade == null) {
            quantidade = 0;
        }

        this.setTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
    }
}

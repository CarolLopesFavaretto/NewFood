package com.newfood.delivery.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Orders {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal subtotal;
    private BigDecimal feeShipping;
    private BigDecimal amount;

    @Embedded
    private Address address;

    private StatusOrders status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime dateConfirmation;
    private LocalDateTime dateCancellation;
    private LocalDateTime dateDelivery;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Payment payment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "orders")
    private List<Item> items = new ArrayList<>();

}

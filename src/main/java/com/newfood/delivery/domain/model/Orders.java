package com.newfood.delivery.domain.model;

import com.newfood.delivery.api.exceptions.BusinessException;
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

    @Enumerated(EnumType.STRING)
    private StatusOrders status = StatusOrders.CREATED;

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

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    public void calculateTotalValue() {
        this.subtotal = getItems().stream()
                .map(item -> item.getTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.amount = this.subtotal.add(this.feeShipping);
    }

    public void calculateTotal() {
        getItems().forEach(Item::calculatedPriceTotal);

        this.subtotal = getItems().stream()
                .map(item -> item.getTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.amount = this.subtotal.add(this.feeShipping);
    }

    public void setShipping() {
        setFeeShipping(getRestaurant().getShipping());
    }

    public void assignOrderToItems() {
        getItems().forEach(item -> item.setOrders(this));
    }

    public void approved() {
        setStatus(StatusOrders.CONFIRMED);
        setDateConfirmation(LocalDateTime.now());
    }

    public void canceled() {
        setStatus(StatusOrders.CANCELED);
        setDateCancellation(LocalDateTime.now());
    }

    public void delivered() {
        setStatus(StatusOrders.DELIVERED);
        setDateDelivery(LocalDateTime.now());
    }

    private void setStatus(StatusOrders newStatus) {
        if (getStatus().cannotChangeTo(newStatus)) {
            throw new BusinessException(String.format
                    ("Status do pedido %d não pode ser alterado de %s para %s", getId(),
                            getStatus().getDescription(), newStatus.getDescription()));
        }
        this.status = newStatus;
    }

}

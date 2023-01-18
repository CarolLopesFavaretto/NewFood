package com.newfood.delivery.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant {


    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal shipping;

    @ManyToOne
    @JoinColumn(name = "cuisine_id", nullable = false)
    private Cuisine cuisine;

    @Embedded
    private Address address;

    private Boolean active = Boolean.TRUE;

    private Boolean open = Boolean.FALSE;

    @ManyToMany
    @JoinTable(name = "restaurants_payment", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "payment_id"))
    private Set<Payment> payment = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "restaurants_user_responsible", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "restaurants_products", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void active() {
        setActive(true);
    }

    public void inactive() {
        setActive(false);
    }

    public void open() {
        setOpen(true);
    }

    public void close() {
        setOpen(false);
    }

    public Boolean removeUser(User user) {
        return getUsers().remove(user);
    }

    public Boolean addUser(User user) {
        return getUsers().add(user);
    }

    public Boolean acceptPaymentMethod(Payment payment) {
        return getPayment().contains(payment);
    }

    public Boolean doesNotAcceptPaymentMethod(Payment payment) {
        return !acceptPaymentMethod(payment);
    }

    public Boolean removePayment(Payment payment) {
        return getPayment().remove(payment);
    }

    public Boolean addPayment(Payment payment) {
        return getPayment().add(payment);
    }


}

package com.newfood.delivery.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusOrders {

    CREATED("Criado"),
    CONFIRMED("Confirmado", CREATED),
    DELIVERED("Entregue", CONFIRMED),
    CANCELED("Cancelado", CREATED);

    private String description;
    private List<StatusOrders> previousStatus;

    StatusOrders(String description, StatusOrders... previousStatus) {
        this.description = description;
        this.previousStatus = Arrays.asList(previousStatus);
    }

    public String getDescription() {
        return this.description;
    }

    public boolean cannotChangeTo(StatusOrders newStatus) {
        return !newStatus.previousStatus.contains(this);
    }

}

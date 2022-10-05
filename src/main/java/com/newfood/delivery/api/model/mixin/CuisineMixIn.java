package com.newfood.delivery.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.newfood.delivery.domain.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public abstract class CuisineMixIn {

    @JsonIgnore
    private List<Restaurant> restaurants = new ArrayList<>();

}

package com.newfood.delivery.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.newfood.delivery.domain.model.State;

public abstract class CityMixIn {

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private State state;
}

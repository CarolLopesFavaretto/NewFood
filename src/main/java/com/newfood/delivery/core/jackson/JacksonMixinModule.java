package com.newfood.delivery.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.newfood.delivery.api.model.mixin.RestaurantMixin;
import com.newfood.delivery.domain.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
    }
}

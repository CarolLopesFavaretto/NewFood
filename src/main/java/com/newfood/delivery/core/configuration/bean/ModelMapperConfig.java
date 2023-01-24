package com.newfood.delivery.core.configuration.bean;

import com.newfood.delivery.domain.model.Item;
import com.newfood.delivery.dto.request.ItemRequest;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper mapper() {
        var mapper = new ModelMapper();

        mapper.createTypeMap(ItemRequest.class, Item.class)
                .addMappings(map -> map.skip(Item::setId));

        return mapper;
    }


}

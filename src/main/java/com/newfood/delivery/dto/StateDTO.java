package com.newfood.delivery.dto;

import com.newfood.delivery.domain.model.State;
import com.newfood.delivery.dto.request.StateRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StateDTO {

    @Autowired
    private ModelMapper mapper;

    public State toObject(StateRequest request) {
        return mapper.map(StateRequest.class, State.class);
    }

    public StateRequest toModel(State state) {
        return mapper.map(State.class, StateRequest.class);
    }

    public List<StateRequest> toCollectionModel(List<State> states) {
        return states.stream()
                .map(state -> toModel(state))
                .collect(Collectors.toList());
    }

    public void updateToObject(StateRequest request, State state) {
        mapper.map(request, state);
    }
}

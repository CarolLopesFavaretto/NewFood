package com.newfood.delivery.dto;

import com.newfood.delivery.domain.model.State;
import com.newfood.delivery.dto.request.StateRequest;
import com.newfood.delivery.dto.response.StateResponse;
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
        return mapper.map(request, State.class);
    }

    public StateResponse toModel(State state) {
        return mapper.map(state, StateResponse.class);
    }

    public List<StateResponse> toCollectionModel(List<State> states) {
        return states.stream()
                .map(state -> toModel(state))
                .collect(Collectors.toList());
    }

    public void updateToObject(StateRequest request, State state) {
        mapper.map(request, state);
    }
}

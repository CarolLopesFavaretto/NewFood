package com.newfood.delivery.domain.repository;

import com.newfood.delivery.domain.model.State;

import java.util.List;

public interface StateRepository {

    List<State> list();
    State findById( Long id);
    State save (State state);
    void delete (Long id);
}

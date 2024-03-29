package com.newfood.delivery.dto;

import com.newfood.delivery.domain.model.User;
import com.newfood.delivery.dto.request.UserRequest;
import com.newfood.delivery.dto.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDTO {

    @Autowired
    private ModelMapper mapper;


    public User toObject(UserRequest request) {
        return mapper.map(request, User.class);
    }

    public UserResponse toModel(User user) {
        return mapper.map(user, UserResponse.class);
    }

    public List<UserResponse> toCollectionModel(Collection<User> users) {
        return users.stream()
                .map(user -> toModel(user))
                .collect(Collectors.toList());
    }

    public void updatedToObject(UserRequest request, User user) {
        user.setName(new String());
        user.setEmail(new String());
        mapper.map(request, user);
    }

}

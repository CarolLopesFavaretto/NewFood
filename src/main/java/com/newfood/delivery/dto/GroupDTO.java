package com.newfood.delivery.dto;

import com.newfood.delivery.domain.model.Group;
import com.newfood.delivery.dto.request.GroupRequest;
import com.newfood.delivery.dto.response.GroupResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupDTO {

    @Autowired
    private ModelMapper mapper;

    public Group toObject(GroupRequest request) {
        return mapper.map(request, Group.class);
    }

    public GroupResponse toModel(Group group) {
        return mapper.map(group, GroupResponse.class);
    }

    public List<GroupResponse> toCollectionModel(List<Group> groups) {
        return groups.stream()
                .map(group -> toModel(group))
                .collect(Collectors.toList());
    }

    public void updatedToObject(GroupRequest request, Group group) {
        group.setName(new String());
        mapper.map(request, group);
    }
}

package com.opencode.healthplusplus.profile.mapping;

import com.opencode.healthplusplus.profile.domain.entity.User;
import com.opencode.healthplusplus.profile.resource.CreateUserResource;
import com.opencode.healthplusplus.profile.resource.UpdateUserResource;
import com.opencode.healthplusplus.profile.resource.UserResource;
import com.opencode.healthplusplus.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UserMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    // Object Mapping

    public UserResource toResource(User model) {
        return mapper.map(model, UserResource.class);
    }

    public Page<UserResource> modelListToPage(List<User> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, UserResource.class),
                pageable,
                modelList.size());
    }

    public User toModel(CreateUserResource resource) {
        return mapper.map(resource, User.class);
    }

    public User toModel(UpdateUserResource resource) {
        return mapper.map(resource, User.class);
    }
}

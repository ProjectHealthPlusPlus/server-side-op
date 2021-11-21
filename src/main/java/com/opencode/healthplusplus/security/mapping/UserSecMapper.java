package com.opencode.healthplusplus.security.mapping;

import com.opencode.healthplusplus.security.domain.model.entity.Role;
import com.opencode.healthplusplus.security.domain.model.entity.UserSec;
import com.opencode.healthplusplus.security.resource.UserSecResource;
import com.opencode.healthplusplus.shared.mapping.EnhancedModelMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UserSecMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    Converter<Role, String> roleToString = new AbstractConverter<>() {
        @Override
        protected String convert(Role role) {
            return role == null ? null : role.getName().name();
        }
    };

    // Object Mapping
    public UserSecResource toResource(UserSec model) {
        mapper.addConverter(roleToString);
        return mapper.map(model, UserSecResource.class);
    }

    public Page<UserSecResource> modelListToPage(List<UserSec> modelList, Pageable pageable) {
        mapper.addConverter(roleToString);
        return new PageImpl<>(mapper.mapList(modelList, UserSecResource.class), pageable, modelList.size());
    }

}

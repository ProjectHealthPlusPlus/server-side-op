package com.opencode.healthplusplus.meeting.mapping;

import com.opencode.healthplusplus.meeting.domain.entity.Location;
import com.opencode.healthplusplus.meeting.resource.LocationResource;
import com.opencode.healthplusplus.meeting.resource.CreateLocationResource;
import com.opencode.healthplusplus.meeting.resource.UpdateLocationResource;
import com.opencode.healthplusplus.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class LocationMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    // Object Mapping

    public LocationResource toResource(Location model) {
        return mapper.map(model, LocationResource.class);
    }

    public Page<LocationResource> modelListToPage(List<Location> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, LocationResource.class),
                pageable,
                modelList.size());
    }

    public Location toModel(CreateLocationResource resource) {
        return mapper.map(resource, Location.class);
    }

    public Location toModel(UpdateLocationResource resource) {
        return mapper.map(resource, Location.class);
    }
}

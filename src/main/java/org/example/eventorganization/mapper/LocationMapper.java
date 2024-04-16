package org.example.eventorganization.mapper;

import org.example.eventorganization.dto.request.location.CreateLocationRequest;
import org.example.eventorganization.dto.request.location.UpdateLocationRequest;
import org.example.eventorganization.dto.response.location.GetLocationResponse;
import org.example.eventorganization.model.Location;

public interface LocationMapper {
    GetLocationResponse mapToResponse(Location location);
    Location mapToLocation(CreateLocationRequest request);
    void mapForUpdate(Location location, UpdateLocationRequest request);
}

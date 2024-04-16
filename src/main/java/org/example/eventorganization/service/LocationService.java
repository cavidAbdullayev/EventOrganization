package org.example.eventorganization.service;

import org.example.eventorganization.dto.request.location.CreateLocationRequest;
import org.example.eventorganization.dto.request.location.UpdateLocationRequest;
import org.example.eventorganization.dto.response.location.GetLocationResponse;
import org.example.eventorganization.model.Location;

import java.util.List;

public interface LocationService {
    GetLocationResponse getById(Integer id);
    List<GetLocationResponse>getAll();
    CreateLocationRequest add(CreateLocationRequest request);
    GetLocationResponse update(Integer id,UpdateLocationRequest request);
    GetLocationResponse deleteById(Integer id);
}

package org.example.eventorganization.service;

import org.example.eventorganization.dto.request.city.CreateCityRequest;
import org.example.eventorganization.dto.request.city.UpdateCityRequest;
import org.example.eventorganization.dto.response.city.GetCityResponse;

import java.util.List;

public interface CityService {
    GetCityResponse getById(Integer id);
    List<GetCityResponse>getAll();
    CreateCityRequest add(CreateCityRequest request);
    GetCityResponse update(Integer id, UpdateCityRequest request);
    GetCityResponse deleteById(Integer id);
}

package org.example.eventorganization.mapper;

import org.example.eventorganization.dto.request.city.CreateCityRequest;
import org.example.eventorganization.dto.request.city.UpdateCityRequest;
import org.example.eventorganization.dto.response.city.GetCityResponse;
import org.example.eventorganization.model.City;

public interface CityMapper {
    GetCityResponse mapToResponse(City city);
    City mapToCity(CreateCityRequest request);
    void mapForUpdate(City city, UpdateCityRequest request);
}

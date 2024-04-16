package org.example.eventorganization.mapper.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.eventorganization.dto.request.city.CreateCityRequest;
import org.example.eventorganization.dto.request.city.UpdateCityRequest;
import org.example.eventorganization.dto.response.city.GetCityResponse;
import org.example.eventorganization.mapper.CityMapper;
import org.example.eventorganization.model.City;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CityMapperImpl implements CityMapper {

    @Override
    public GetCityResponse mapToResponse(City city) {
        return GetCityResponse.builder()
                .area(city.getArea())
                .name(city.getName())
                .population(city.getPopulation())
                .establishedYear(city.getEstablishedYear())
                .build();
    }

    @Override
    public City mapToCity(CreateCityRequest request) {
        return City.builder()
                .area(request.getArea())
                .establishedYear(request.getEstablishedYear())
                .name(request.getName())
                .population(request.getPopulation())
                .build();
    }

    @Override
    public void mapForUpdate(City city, UpdateCityRequest request) {
        if(request.getArea()!=null)
            city.setArea(request.getArea());
        if(request.getName()!=null)
            city.setName(request.getName());
        if(request.getPopulation()!=null)
            city.setPopulation(request.getPopulation());
        if(request.getEstablishedYear()!=null)
            city.setEstablishedYear(request.getEstablishedYear());
    }
}

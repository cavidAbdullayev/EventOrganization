package org.example.eventorganization.mapper.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.eventorganization.dto.request.location.CreateLocationRequest;
import org.example.eventorganization.dto.request.location.UpdateLocationRequest;
import org.example.eventorganization.dto.response.city.GetCityResponse;
import org.example.eventorganization.dto.response.location.GetLocationResponse;
import org.example.eventorganization.mapper.CityMapper;
import org.example.eventorganization.mapper.LocationMapper;
import org.example.eventorganization.model.City;
import org.example.eventorganization.model.Location;
import org.example.eventorganization.repository.CityRepository;
import org.example.eventorganization.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class LocationMapperImpl implements LocationMapper {
    CityRepository cityRepository;
    CityMapper cityMapper;
    LocationRepository locationRepository;
    @Override
    public GetLocationResponse mapToResponse(Location location) {
        GetCityResponse cityResponse=cityMapper.mapToResponse(location.getCity());
        return GetLocationResponse.builder()
                .postalCode(location.getPostalCode())
                .budget(location.getBudget())
                .address(location.getAddress())
                .cityResponse(cityResponse)
                .build();
    }

    @Override
    public Location mapToLocation(CreateLocationRequest request) {
        City city=cityRepository.findById(request.getCityId()).orElseThrow(()->
                new RuntimeException("City given by id not found!"));
        if(locationRepository.existsByPostalCode(request.getPostalCode()))
            throw new RuntimeException("Location given by postal code already exists!");
        if(locationRepository.existsByAddress(request.getAddress()))
            throw new RuntimeException("Location given by address already exists!");
        return Location.builder()
                .postalCode(request.getPostalCode())
                .budget(request.getBudget())
                .address(request.getAddress())
                .city(city)
                .build();
    }

    @Override
    public void mapForUpdate(Location location, UpdateLocationRequest request) {
        if(request.getCityId()!=null){
            City city=cityRepository.findById(request.getCityId()).orElseThrow(()->
                    new RuntimeException("City given by id not found!"));
        location.setCity(city);
        }
        if(request.getAddress()!=null){
            if(!locationRepository.existsByAddress(request.getAddress()))
                throw new RuntimeException("Location given by address already exists!");
            location.setAddress(request.getAddress());
        }
        if(request.getBudget()!=null)
            location.setBudget(request.getBudget());
        if(request.getPostalCode()!=null){
            if(locationRepository.existsByPostalCode(request.getPostalCode()))
                throw new RuntimeException("Location given by postal code already exists!");
            location.setPostalCode(request.getPostalCode());}
    }
}

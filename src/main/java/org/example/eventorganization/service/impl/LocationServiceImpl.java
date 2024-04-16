package org.example.eventorganization.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.eventorganization.dto.request.location.CreateLocationRequest;
import org.example.eventorganization.dto.request.location.UpdateLocationRequest;
import org.example.eventorganization.dto.response.location.GetLocationResponse;
import org.example.eventorganization.mapper.LocationMapper;
import org.example.eventorganization.model.Location;
import org.example.eventorganization.repository.LocationRepository;
import org.example.eventorganization.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class LocationServiceImpl implements LocationService {
    LocationRepository locationRepository;
    LocationMapper locationMapper;


    @Override
    public GetLocationResponse getById(Integer id) {
        return locationMapper.mapToResponse(locationRepository.findById(id).orElseThrow(()->
                new RuntimeException("Location given by id not found!")));
    }

    @Override
    public List<GetLocationResponse> getAll() {
        return locationRepository.findAll()
                .stream()
                .map(locationMapper::mapToResponse)
                .toList();
    }

    @Override
    public CreateLocationRequest add(CreateLocationRequest request) {
        Location location=locationMapper.mapToLocation(request);
        locationRepository.save(location);
        return request;
    }

    @Override
    public GetLocationResponse update(Integer id, UpdateLocationRequest request) {
        Location location=locationRepository.findById(id).orElseThrow(()->
                new RuntimeException("Location given by id not found!"));
        locationMapper.mapForUpdate(location,request);
        locationRepository.save(location);
        return locationMapper.mapToResponse(location);
    }

    @Override
    public GetLocationResponse deleteById(Integer id) {
        Location location=locationRepository.findById(id).orElseThrow(()->
                new RuntimeException("Location given by id not found!"));
        locationRepository.deleteById(id);
        return locationMapper.mapToResponse(location);
    }
}

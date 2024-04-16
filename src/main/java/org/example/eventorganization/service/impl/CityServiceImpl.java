package org.example.eventorganization.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.eventorganization.dto.request.city.CreateCityRequest;
import org.example.eventorganization.dto.request.city.UpdateCityRequest;
import org.example.eventorganization.dto.response.city.GetCityResponse;
import org.example.eventorganization.mapper.CityMapper;
import org.example.eventorganization.model.City;
import org.example.eventorganization.repository.CityRepository;
import org.example.eventorganization.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    CityRepository cityRepository;
    CityMapper cityMapper;
    @Override
    public GetCityResponse getById(Integer id) {
        return cityMapper.mapToResponse(cityRepository.findById(id).orElseThrow(()->
                new RuntimeException("City given by id not found!")));
    }

    @Override
    public List<GetCityResponse> getAll() {
        return cityRepository.findAll()
                .stream()
                .map(cityMapper::mapToResponse)
                .toList();
    }

    @Override
    public CreateCityRequest add(CreateCityRequest request) {
        City city=cityMapper.mapToCity(request);
        cityRepository.save(city);
        return request;
    }

    @Override
    public GetCityResponse update(Integer id, UpdateCityRequest request) {
        City city=cityRepository.findById(id).orElseThrow(()->
                new RuntimeException("City given by id not found!"));
        cityMapper.mapForUpdate(city,request);
        cityRepository.save(city);
        return cityMapper.mapToResponse(city);
    }

    @Override
    public GetCityResponse deleteById(Integer id) {
        City city=cityRepository.findById(id).orElseThrow(()->
                new RuntimeException("City given by id not found!"));
        cityRepository.deleteById(id);
        return cityMapper.mapToResponse(city);
    }
}

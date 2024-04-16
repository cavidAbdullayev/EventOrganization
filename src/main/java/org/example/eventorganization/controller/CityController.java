package org.example.eventorganization.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.example.eventorganization.dto.request.city.CreateCityRequest;
import org.example.eventorganization.dto.request.city.UpdateCityRequest;
import org.example.eventorganization.dto.response.city.GetCityResponse;
import org.example.eventorganization.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CityController {
CityService cityService;
@GetMapping("/get-by-id/{id}")
public GetCityResponse getById(@PathVariable Integer id){
        return cityService.getById(id);
    }
    @GetMapping("/get-all")
    public List<GetCityResponse> getAll(){
        return cityService.getAll();
    }
    @PostMapping("/add")
    public CreateCityRequest add(@RequestBody @Valid CreateCityRequest request){
        return cityService.add(request);
    }
    @PutMapping("/update/{id}")
    public GetCityResponse update(@PathVariable Integer id,
                                  @RequestBody @Valid UpdateCityRequest request){
        return cityService.update(id,request);
    }
    @GetMapping("/delete-by-id/{id}")
    public GetCityResponse deleteById(@PathVariable Integer id){
        return cityService.deleteById(id);
    }
}

package org.example.eventorganization.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.eventorganization.dto.request.location.CreateLocationRequest;
import org.example.eventorganization.dto.request.location.UpdateLocationRequest;
import org.example.eventorganization.dto.response.location.GetLocationResponse;
import org.example.eventorganization.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/location")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class LocationController {
    LocationService locationService;
    @GetMapping("/get-by-id/{id}")
    public GetLocationResponse getById(@PathVariable Integer id){
        return locationService.getById(id);
    }
    @GetMapping("/get-all")
    public List<GetLocationResponse> getAll(){
        return locationService.getAll();
    }
    @PostMapping("/add")
    public CreateLocationRequest add(@RequestBody @Valid CreateLocationRequest request){
        return locationService.add(request);
    }
    @PutMapping("/update/{id}")
    public GetLocationResponse update(@PathVariable Integer id,
                                      @RequestBody @Valid UpdateLocationRequest request){
        return locationService.update(id,request);
    }
    @DeleteMapping("/delete-by-id/{id}")
    public GetLocationResponse deleteById(@PathVariable Integer id){
        return locationService.deleteById(id);
    }
}
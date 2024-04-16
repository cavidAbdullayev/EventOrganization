package org.example.eventorganization.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.eventorganization.dto.request.room.CreateRoomRequest;
import org.example.eventorganization.dto.request.room.UpdateRoomRequest;
import org.example.eventorganization.dto.response.room.GetRoomResponse;
import org.example.eventorganization.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RestController
public class RoomController {
    RoomService roomService;
    @GetMapping("/get-by-id/{id}")
    public GetRoomResponse getById(@PathVariable Integer id){
        return roomService.getById(id);
    }
    @GetMapping("/get-all")
    public List<GetRoomResponse> getAll(){
        return roomService.getAll();
    }
    @PostMapping("/add")
    public CreateRoomRequest add(@RequestBody @Valid CreateRoomRequest request){
        return roomService.add(request);
    }
    @PutMapping("/update/{id}")
    public GetRoomResponse update(@PathVariable Integer id,
                                  @RequestBody @Valid UpdateRoomRequest request){
        return roomService.update(id,request);
    }
    @DeleteMapping("/delete-by-id/{id}")
    public GetRoomResponse deleteById(@PathVariable Integer id){
        return roomService.deleteById(id);
    }
}

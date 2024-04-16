package org.example.eventorganization.service;

import org.example.eventorganization.dto.request.room.CreateRoomRequest;
import org.example.eventorganization.dto.request.room.UpdateRoomRequest;
import org.example.eventorganization.dto.response.room.GetRoomResponse;

import java.util.List;

public interface RoomService {
    GetRoomResponse getById(Integer id);
    List<GetRoomResponse>getAll();
    CreateRoomRequest add(CreateRoomRequest request);
    GetRoomResponse update(Integer id, UpdateRoomRequest request);
    GetRoomResponse deleteById(Integer id);
}

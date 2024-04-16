package org.example.eventorganization.mapper;

import org.example.eventorganization.dto.request.room.CreateRoomRequest;
import org.example.eventorganization.dto.request.room.UpdateRoomRequest;
import org.example.eventorganization.dto.response.room.GetRoomResponse;
import org.example.eventorganization.model.Room;

public interface RoomMapper {
    GetRoomResponse mapToResponse(Room room);
    Room mapToRoom(CreateRoomRequest request);
    void mapForUpdate(UpdateRoomRequest request,Room room);

}

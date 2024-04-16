package org.example.eventorganization.mapper.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.eventorganization.dto.request.room.CreateRoomRequest;
import org.example.eventorganization.dto.request.room.UpdateRoomRequest;
import org.example.eventorganization.dto.response.room.GetRoomResponse;
import org.example.eventorganization.mapper.RoomMapper;
import org.example.eventorganization.model.Room;
import org.example.eventorganization.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class RoomMapperImpl implements RoomMapper {
    RoomRepository roomRepository;
    @Override
    public GetRoomResponse mapToResponse(Room room) {
        return GetRoomResponse.builder()
                .roomNumber(room.getRoomNumber())
                .capacity(room.getCapacity())
                .build();
    }

    @Override
    public Room mapToRoom(CreateRoomRequest request) {
        if(roomRepository.existsByRoomNumber(request.getRoomNumber()))
            throw new RuntimeException("Room given by number already exists!");
        if(request.getCapacity()<=0)
            throw new RuntimeException("Room capacity cannot be equal or less than 0!");
        if(request.getCapacity()<=10)
            throw new RuntimeException("Room is so small for event!");
        return Room.builder()
                .roomNumber(request.getRoomNumber())
                .capacity(request.getCapacity())
                .build();
    }

    @Override
    public void mapForUpdate(UpdateRoomRequest request, Room room) {
        if(request.getCapacity()!=null){
            if(request.getCapacity()<=0)
                throw new RuntimeException("Room capacity cannot be equal or less than 0!");
            if(request.getCapacity()<=10)
                throw new RuntimeException("Room is so small for event!");
            room.setCapacity(request.getCapacity());
        }
        if(request.getRoomNumber()!=null){
            if(roomRepository.existsByRoomNumber(request.getRoomNumber()))
                throw new RuntimeException("Room given by id already exists!");
            room.setRoomNumber(request.getRoomNumber());
    }
    }
}
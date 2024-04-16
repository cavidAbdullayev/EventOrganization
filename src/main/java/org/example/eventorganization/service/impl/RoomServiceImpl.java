package org.example.eventorganization.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.eventorganization.dto.request.room.CreateRoomRequest;
import org.example.eventorganization.dto.request.room.UpdateRoomRequest;
import org.example.eventorganization.dto.response.room.GetRoomResponse;
import org.example.eventorganization.mapper.RoomMapper;
import org.example.eventorganization.model.Room;
import org.example.eventorganization.repository.RoomRepository;
import org.example.eventorganization.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoomServiceImpl implements RoomService {
    RoomMapper roomMapper;
    RoomRepository roomRepository;
    @Override
    public GetRoomResponse getById(Integer id) {
        return roomMapper.mapToResponse(roomRepository.findById(id).orElseThrow(()->
                new RuntimeException("Room given by id not found!")));
    }
    @Override
    public List<GetRoomResponse> getAll() {
        return roomRepository.findAll()
                .stream()
                .map(roomMapper::mapToResponse)
                .toList();
    }

    @Override
    public CreateRoomRequest add(CreateRoomRequest request) {
        Room room=roomMapper.mapToRoom(request);
        roomRepository.save(room);
        return request;
    }

    @Override
    public GetRoomResponse update(Integer id, UpdateRoomRequest request) {
        Room room=roomRepository.findById(id).orElseThrow(()->
                new RuntimeException("Room given by id not found!"));
        roomMapper.mapForUpdate(request,room);
        roomRepository.save(room);
        return roomMapper.mapToResponse(room);
    }

    @Override
    public GetRoomResponse deleteById(Integer id) {
        Room room=roomRepository.findById(id).orElseThrow(()->
                new RuntimeException("Room given by id not found!"));
        roomRepository.deleteById(id);
        return roomMapper.mapToResponse(room);
    }
}

package org.example.eventorganization.repository;

import org.example.eventorganization.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    boolean existsByRoomNumber(String roomNumber);
}

package com.ho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ho.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    // Find all rooms by type (e.g., ICU, GENERAL, PRIVATE)
    List<Room> findByRoomType(String roomtype);

    // Find all available rooms (assuming a boolean field 'isAvailable' exists)
    List<Room> findByIsAvailableTrue();

    // Check if a room number already exists
    boolean existsByRoomNumber(String roomNumber);

    // Find room by room number
    Room findByRoomNumber(String roomNumber);
}

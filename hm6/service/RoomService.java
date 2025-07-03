package com.ho.service;

import java.util.List;

import com.ho.entity.Room;

public interface RoomService {

    Room createRoom(Room room);

    Room updateRoom(Long id, Room room);

    Room getRoomById(Long id);

    List<Room> getAllRooms();

    void deleteRoom(Long id);
}

package com.ho.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ho.entity.Room;
import com.ho.exception.ResourceNotFoundException;
import com.ho.repository.RoomRepository;
import com.ho.service.RoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Long id, Room updatedRoom) {
        Room existing = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));

        existing.setRoomNumber(updatedRoom.getRoomNumber());
        existing.setRoomType(updatedRoom.getRoomType());
        existing.setIsAvailable(updatedRoom.getIsAvailable());

        return roomRepository.save(existing);
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public void deleteRoom(Long id) {
        Room existing = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
        roomRepository.delete(existing);
    }
}

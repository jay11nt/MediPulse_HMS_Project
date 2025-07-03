package com.ho.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ho.entity.Room;
import com.ho.service.RoomService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    // Create a new room
    @PostMapping
    public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room) {
        Room created = roomService.createRoom(room);
        return ResponseEntity.ok(created);
    }

    // Update an existing room
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @Valid @RequestBody Room room) {
        Room updated = roomService.updateRoom(id, room);
        return ResponseEntity.ok(updated);
    }

    // Get a room by ID
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }

    // Get all rooms
    @GetMapping("/getall")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    // Delete a room by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}

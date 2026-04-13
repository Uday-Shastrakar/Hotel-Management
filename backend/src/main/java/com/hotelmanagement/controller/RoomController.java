package com.hotelmanagement.controller;

import com.hotelmanagement.dto.RoomDTO;
import com.hotelmanagement.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/available")
    public ResponseEntity<List<RoomDTO>> getAvailableRooms(
            @RequestParam Long hotelId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(roomService.getAvailableRooms(hotelId, startDate, endDate));
    }

    @PostMapping("/hotel/{hotelId}")
    public ResponseEntity<RoomDTO> addRoom(@PathVariable Long hotelId, @RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok(roomService.addRoom(hotelId, roomDTO));
    }
}

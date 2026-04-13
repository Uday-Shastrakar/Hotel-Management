package com.hotelmanagement.controller;

import com.hotelmanagement.dto.BookingDTO;
import com.hotelmanagement.dto.BookingRequest;
import com.hotelmanagement.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDTO> bookRoom(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.bookRoom(
                request.getUserId(),
                request.getRoomId(),
                request.getStartDate(),
                request.getEndDate()
        ));
    }
}

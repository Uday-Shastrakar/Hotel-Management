package com.hotelmanagement.controller;

import com.hotelmanagement.dto.HotelDTO;
import com.hotelmanagement.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<Page<HotelDTO>> searchHotels(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Double minRating,
            Pageable pageable) {
        return ResponseEntity.ok(hotelService.searchHotels(city, minRating, pageable));
    }

    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotel) {
        return ResponseEntity.ok(hotelService.createHotel(hotel));
    }
}

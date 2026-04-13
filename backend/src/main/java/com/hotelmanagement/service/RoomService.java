package com.hotelmanagement.service;

import com.hotelmanagement.dto.RoomDTO;
import com.hotelmanagement.entity.Hotel;
import com.hotelmanagement.entity.Room;
import com.hotelmanagement.mapper.RoomMapper;
import com.hotelmanagement.repository.HotelRepository;
import com.hotelmanagement.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final RoomMapper roomMapper;

    @Transactional(readOnly = true)
    public List<RoomDTO> getAvailableRooms(Long hotelId, LocalDate startDate, LocalDate endDate) {
        return roomRepository.findAvailableRooms(hotelId, startDate, endDate)
                .stream()
                .map(roomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public RoomDTO addRoom(Long hotelId, RoomDTO roomDTO) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        
        Room room = roomMapper.toEntity(roomDTO);
        room.setHotel(hotel);
        Room saved = roomRepository.save(room);
        return roomMapper.toDTO(saved);
    }
}

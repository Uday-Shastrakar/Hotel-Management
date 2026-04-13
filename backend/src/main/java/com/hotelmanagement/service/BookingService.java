package com.hotelmanagement.service;

import com.hotelmanagement.dto.BookingDTO;
import com.hotelmanagement.entity.Booking;
import com.hotelmanagement.entity.BookingStatus;
import com.hotelmanagement.entity.Room;
import com.hotelmanagement.exception.ResourceNotFoundException;
import com.hotelmanagement.mapper.BookingMapper;
import com.hotelmanagement.repository.BookingRepository;
import com.hotelmanagement.repository.RoomRepository;
import com.hotelmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import com.hotelmanagement.messaging.BookingEventProducer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;
    private final BookingEventProducer bookingEventProducer;

    @Transactional
    public BookingDTO bookRoom(Long userId, Long roomId, LocalDate startDate, LocalDate endDate) {
        Room room = roomRepository.findByIdWithLock(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        if (!room.getAvailable()) throw new RuntimeException("Room not available");
        if (bookingRepository.existsOverlappingBooking(roomId, startDate, endDate))
            throw new RuntimeException("Room booked for these dates");

        Booking booking = Booking.builder()
                .user(userRepository.findById(userId).orElseThrow())
                .room(room)
                .startDate(startDate)
                .endDate(endDate)
                .status(BookingStatus.CONFIRMED).build();

        Booking saved = bookingRepository.save(booking);
        bookingEventProducer.sendBookingCreated(saved.getId());
        return bookingMapper.toDTO(saved);
    }
}

package com.hotelmanagement.config;

import com.hotelmanagement.entity.Hotel;
import com.hotelmanagement.entity.Room;
import com.hotelmanagement.entity.User;
import com.hotelmanagement.repository.HotelRepository;
import com.hotelmanagement.repository.RoomRepository;
import com.hotelmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            log.info("Seeding initial data...");

            User user = User.builder()
                    .name("Test User")
                    .email("test@example.com")
                    .phone("1234567890")
                    .build();
            userRepository.save(user);

            Hotel hotel1 = Hotel.builder()
                    .name("Grand Palace Mumbai")
                    .city("Mumbai")
                    .rating(4.5)
                    .description("A luxury stay in the heart of Mumbai.")
                    .build();

            Hotel hotel2 = Hotel.builder()
                    .name("Seaside Resort Goa")
                    .city("Goa")
                    .rating(4.8)
                    .description("Relaxing stay by the beach.")
                    .build();

            hotelRepository.saveAll(List.of(hotel1, hotel2));

            Room room1 = Room.builder()
                    .hotel(hotel1)
                    .roomNumber("101")
                    .type("DELUXE")
                    .price(new BigDecimal("5000.00"))
                    .available(true)
                    .build();

            Room room2 = Room.builder()
                    .hotel(hotel1)
                    .roomNumber("102")
                    .type("SUITE")
                    .price(new BigDecimal("8000.00"))
                    .available(true)
                    .build();

            Room room3 = Room.builder()
                    .hotel(hotel2)
                    .roomNumber("G-01")
                    .type("VILLA")
                    .price(new BigDecimal("12000.00"))
                    .available(true)
                    .build();

            roomRepository.saveAll(List.of(room1, room2, room3));
            log.info("Seeding complete.");
        }
    }
}

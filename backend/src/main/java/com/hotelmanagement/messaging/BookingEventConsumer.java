package com.hotelmanagement.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingEventConsumer {

    @KafkaListener(topics = "booking_events", groupId = "hotel-management-group")
    public void consumeBookingEvent(String message) {
        log.info("Consumed booking event: {}", message);
        // Additional logic like updating internal vacancy counters or sending alerts could go here
    }
}

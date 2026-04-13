package com.hotelmanagement.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "booking_events";

    public void sendBookingCreated(Long bookingId) {
        log.info("Producing booking event for ID: {}", bookingId);
        kafkaTemplate.send(TOPIC, "CREATED:" + bookingId);
    }
}

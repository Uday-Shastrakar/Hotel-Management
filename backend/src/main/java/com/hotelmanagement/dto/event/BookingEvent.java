package com.hotelmanagement.dto.event;

import lombok.*;
import com.hotelmanagement.entity.BookingStatus;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingEvent {
    private String eventType; // BOOKING_CREATED, BOOKING_CANCELLED
    private Long bookingId;
    private Long userId;
    private Long roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BookingStatus status;
}

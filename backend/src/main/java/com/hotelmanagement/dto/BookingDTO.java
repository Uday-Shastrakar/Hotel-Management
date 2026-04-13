package com.hotelmanagement.dto;

import lombok.*;
import java.time.LocalDate;
import com.hotelmanagement.entity.BookingStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDTO {
    private Long id;
    private Long userId;
    private Long roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BookingStatus status;
}

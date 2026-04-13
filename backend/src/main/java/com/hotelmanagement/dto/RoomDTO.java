package com.hotelmanagement.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDTO {
    private Long id;
    private Long hotelId;
    private String roomNumber;
    private String type;
    private BigDecimal price;
    private Integer capacity;
    private Boolean available;
}

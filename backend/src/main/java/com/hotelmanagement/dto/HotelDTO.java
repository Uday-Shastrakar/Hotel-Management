package com.hotelmanagement.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDTO {
    private Long id;
    private String name;
    private String city;
    private Double rating;
    private String description;
    private List<RoomDTO> rooms;
}

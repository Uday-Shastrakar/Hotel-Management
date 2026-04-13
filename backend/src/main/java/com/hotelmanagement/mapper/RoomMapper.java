package com.hotelmanagement.mapper;

import com.hotelmanagement.dto.RoomDTO;
import com.hotelmanagement.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mapping(source = "hotel.id", target = "hotelId")
    RoomDTO toDTO(Room room);

    @Mapping(source = "hotelId", target = "hotel.id")
    Room toEntity(RoomDTO roomDTO);
}

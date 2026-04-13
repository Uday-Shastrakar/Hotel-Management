package com.hotelmanagement.mapper;

import com.hotelmanagement.dto.HotelDTO;
import com.hotelmanagement.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoomMapper.class})
public interface HotelMapper {
    HotelDTO toDTO(Hotel hotel);
    Hotel toEntity(HotelDTO hotelDTO);
}

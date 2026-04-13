package com.hotelmanagement.mapper;

import com.hotelmanagement.dto.RoomDTO;
import com.hotelmanagement.entity.Hotel;
import com.hotelmanagement.entity.Room;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-13T12:02:01+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomDTO toDTO(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomDTO.RoomDTOBuilder roomDTO = RoomDTO.builder();

        roomDTO.hotelId( roomHotelId( room ) );
        roomDTO.id( room.getId() );
        roomDTO.roomNumber( room.getRoomNumber() );
        roomDTO.type( room.getType() );
        roomDTO.price( room.getPrice() );
        roomDTO.capacity( room.getCapacity() );
        roomDTO.available( room.getAvailable() );

        return roomDTO.build();
    }

    @Override
    public Room toEntity(RoomDTO roomDTO) {
        if ( roomDTO == null ) {
            return null;
        }

        Room.RoomBuilder room = Room.builder();

        room.hotel( roomDTOToHotel( roomDTO ) );
        room.id( roomDTO.getId() );
        room.roomNumber( roomDTO.getRoomNumber() );
        room.type( roomDTO.getType() );
        room.price( roomDTO.getPrice() );
        room.capacity( roomDTO.getCapacity() );
        room.available( roomDTO.getAvailable() );

        return room.build();
    }

    private Long roomHotelId(Room room) {
        Hotel hotel = room.getHotel();
        if ( hotel == null ) {
            return null;
        }
        return hotel.getId();
    }

    protected Hotel roomDTOToHotel(RoomDTO roomDTO) {
        if ( roomDTO == null ) {
            return null;
        }

        Hotel.HotelBuilder hotel = Hotel.builder();

        hotel.id( roomDTO.getHotelId() );

        return hotel.build();
    }
}

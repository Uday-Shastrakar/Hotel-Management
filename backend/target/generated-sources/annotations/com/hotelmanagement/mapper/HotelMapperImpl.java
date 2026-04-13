package com.hotelmanagement.mapper;

import com.hotelmanagement.dto.HotelDTO;
import com.hotelmanagement.dto.RoomDTO;
import com.hotelmanagement.entity.Hotel;
import com.hotelmanagement.entity.Room;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-13T12:02:00+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class HotelMapperImpl implements HotelMapper {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public HotelDTO toDTO(Hotel hotel) {
        if ( hotel == null ) {
            return null;
        }

        HotelDTO.HotelDTOBuilder hotelDTO = HotelDTO.builder();

        hotelDTO.id( hotel.getId() );
        hotelDTO.name( hotel.getName() );
        hotelDTO.city( hotel.getCity() );
        hotelDTO.rating( hotel.getRating() );
        hotelDTO.description( hotel.getDescription() );
        hotelDTO.rooms( roomListToRoomDTOList( hotel.getRooms() ) );

        return hotelDTO.build();
    }

    @Override
    public Hotel toEntity(HotelDTO hotelDTO) {
        if ( hotelDTO == null ) {
            return null;
        }

        Hotel.HotelBuilder hotel = Hotel.builder();

        hotel.id( hotelDTO.getId() );
        hotel.name( hotelDTO.getName() );
        hotel.city( hotelDTO.getCity() );
        hotel.rating( hotelDTO.getRating() );
        hotel.description( hotelDTO.getDescription() );
        hotel.rooms( roomDTOListToRoomList( hotelDTO.getRooms() ) );

        return hotel.build();
    }

    protected List<RoomDTO> roomListToRoomDTOList(List<Room> list) {
        if ( list == null ) {
            return null;
        }

        List<RoomDTO> list1 = new ArrayList<RoomDTO>( list.size() );
        for ( Room room : list ) {
            list1.add( roomMapper.toDTO( room ) );
        }

        return list1;
    }

    protected List<Room> roomDTOListToRoomList(List<RoomDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Room> list1 = new ArrayList<Room>( list.size() );
        for ( RoomDTO roomDTO : list ) {
            list1.add( roomMapper.toEntity( roomDTO ) );
        }

        return list1;
    }
}

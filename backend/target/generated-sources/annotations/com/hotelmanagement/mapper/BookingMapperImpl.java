package com.hotelmanagement.mapper;

import com.hotelmanagement.dto.BookingDTO;
import com.hotelmanagement.entity.Booking;
import com.hotelmanagement.entity.Room;
import com.hotelmanagement.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-13T12:02:01+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingDTO toDTO(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        BookingDTO.BookingDTOBuilder bookingDTO = BookingDTO.builder();

        bookingDTO.userId( bookingUserId( booking ) );
        bookingDTO.roomId( bookingRoomId( booking ) );
        bookingDTO.id( booking.getId() );
        bookingDTO.startDate( booking.getStartDate() );
        bookingDTO.endDate( booking.getEndDate() );
        bookingDTO.status( booking.getStatus() );

        return bookingDTO.build();
    }

    @Override
    public Booking toEntity(BookingDTO bookingDTO) {
        if ( bookingDTO == null ) {
            return null;
        }

        Booking.BookingBuilder booking = Booking.builder();

        booking.user( bookingDTOToUser( bookingDTO ) );
        booking.room( bookingDTOToRoom( bookingDTO ) );
        booking.id( bookingDTO.getId() );
        booking.startDate( bookingDTO.getStartDate() );
        booking.endDate( bookingDTO.getEndDate() );
        booking.status( bookingDTO.getStatus() );

        return booking.build();
    }

    private Long bookingUserId(Booking booking) {
        User user = booking.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private Long bookingRoomId(Booking booking) {
        Room room = booking.getRoom();
        if ( room == null ) {
            return null;
        }
        return room.getId();
    }

    protected User bookingDTOToUser(BookingDTO bookingDTO) {
        if ( bookingDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( bookingDTO.getUserId() );

        return user.build();
    }

    protected Room bookingDTOToRoom(BookingDTO bookingDTO) {
        if ( bookingDTO == null ) {
            return null;
        }

        Room.RoomBuilder room = Room.builder();

        room.id( bookingDTO.getRoomId() );

        return room.build();
    }
}

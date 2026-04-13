package com.hotelmanagement.repository;

import com.hotelmanagement.entity.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId AND r.id NOT IN (" +
           "SELECT b.room.id FROM Booking b WHERE b.room.hotel.id = :hotelId " +
           "AND NOT (b.endDate <= :startDate OR b.startDate >= :endDate) " +
           "AND b.status != 'CANCELLED')")
    List<Room> findAvailableRooms(@Param("hotelId") Long hotelId, 
                                 @Param("startDate") LocalDate startDate, 
                                 @Param("endDate") LocalDate endDate);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Room r WHERE r.id = :id")
    Optional<Room> findByIdWithLock(@Param("id") Long id);
}

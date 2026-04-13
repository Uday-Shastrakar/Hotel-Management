package com.hotelmanagement.repository;

import com.hotelmanagement.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("SELECT h FROM Hotel h WHERE " +
           "(:city IS NULL OR LOWER(h.city) = LOWER(:city)) AND " +
           "(:rating IS NULL OR h.rating >= :rating)")
    Page<Hotel> searchHotels(@Param("city") String city, @Param("rating") Double rating, Pageable pageable);
}

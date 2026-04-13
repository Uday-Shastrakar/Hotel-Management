package com.hotelmanagement.service;

import com.hotelmanagement.dto.HotelDTO;
import com.hotelmanagement.entity.Hotel;
import com.hotelmanagement.mapper.HotelMapper;
import com.hotelmanagement.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Transactional(readOnly = true)
    public Page<HotelDTO> searchHotels(String city, Double minRating, Pageable pageable) {
        return hotelRepository.searchHotels(city, minRating, pageable)
                .map(hotelMapper::toDTO);
    }

    @Transactional
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        Hotel hotel = hotelMapper.toEntity(hotelDTO);
        Hotel saved = hotelRepository.save(hotel);
        return hotelMapper.toDTO(saved);
    }
}

package com.redhat.hotelbooking.repository;


import com.redhat.hotelbooking.bean.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HotelRepository extends PagingAndSortingRepository<Hotel, Integer> {
    Page<Hotel> findByCityName(Pageable pageable, String cityName);
}
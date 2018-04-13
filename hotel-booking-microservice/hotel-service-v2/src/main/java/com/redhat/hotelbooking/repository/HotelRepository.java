package com.redhat.hotelbooking.repository;


import com.redhat.hotelbooking.bean.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface HotelRepository extends PagingAndSortingRepository<Hotel, Integer> {

    @Query("Select new Hotel(id, name || ' (version2)' as name, stars, price, cityName, countryName, address, available_rooms) "
            + "from Hotel as h "
            + "where h.cityName = :cityName ")
    Page<Hotel> findByCityNameV2(@Param("cityName") String cityName, Pageable pageable);
}
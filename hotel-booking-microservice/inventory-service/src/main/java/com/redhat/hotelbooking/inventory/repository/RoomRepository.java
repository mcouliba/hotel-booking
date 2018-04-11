package com.redhat.hotelbooking.inventory.repository;


import com.redhat.hotelbooking.inventory.bean.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomRepository extends PagingAndSortingRepository<Room, Integer> {
    Page<Room> findByHotelId(Pageable pageable, Integer hotelId);
}
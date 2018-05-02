package com.redhat.hotelbooking.repository;

import com.redhat.hotelbooking.bean.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomRepository extends PagingAndSortingRepository<Room, Integer> {
    Page<Room> findByHotelId(Pageable pageable, Integer hotel_id);
}
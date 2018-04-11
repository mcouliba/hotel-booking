package com.redhat.hotelbooking.inventory.service;

import com.redhat.hotelbooking.inventory.bean.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryService {
    public Page<Room> listRooms(Pageable pageable);
    public Page<Room> findRoomByHotel(Pageable pageable, Integer hotelId);
}
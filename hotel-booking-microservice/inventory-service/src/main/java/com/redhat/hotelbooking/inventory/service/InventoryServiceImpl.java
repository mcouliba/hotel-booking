package com.redhat.hotelbooking.inventory.service;

import com.redhat.hotelbooking.inventory.bean.Room;
import com.redhat.hotelbooking.inventory.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class InventoryServiceImpl implements InventoryService {

	@Autowired
	private RoomRepository roomRepository;

	@Override
	public Page<Room> listRooms(Pageable pageable) {
		return roomRepository.findAll(pageable);
	}

	@Override
	public Page<Room> findRoomByHotel(Pageable pageable, Integer hotelId) {
		return roomRepository.findByHotelId(pageable, hotelId);
	}
}

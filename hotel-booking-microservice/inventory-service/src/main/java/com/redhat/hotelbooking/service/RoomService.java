package com.redhat.hotelbooking.service;

import com.redhat.hotelbooking.bean.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface RoomService {
    public Page<Room> findByUserID(Pageable pageable, String userid) throws IOException;
}
package com.redhat.hotelbooking.service;

import com.redhat.hotelbooking.bean.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface HotelService {
    public Page<Hotel> listAllByPage(Pageable pageable);
    public Page<Hotel> findByUserID(Pageable pageable, String userid) throws IOException;
}
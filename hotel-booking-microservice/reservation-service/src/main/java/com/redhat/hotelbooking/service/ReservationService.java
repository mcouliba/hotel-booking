package com.redhat.hotelbooking.service;

import com.redhat.hotelbooking.bean.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface ReservationService {
    public final String CHECKIN_STATUS = "CHECKIN";
    public final String CHECKOUT_STATUS = "CHECKOUT";

    public Page<Reservation> findByCustomerId(Pageable pageable, Integer customerId) throws IOException;
    public boolean checkin(Integer reservationId);
    public boolean checkout(Integer reservationId);
}
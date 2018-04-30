package com.redhat.hotelbooking.service;

import com.redhat.hotelbooking.bean.Reservation;
import com.redhat.hotelbooking.bean.SourceReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.text.ParseException;

public interface ReservationService {
    public final String CHECKIN_STATUS = "CHECKIN";
    public final String CHECKOUT_STATUS = "CHECKOUT";

    public Page<Reservation> findByCustomerId(Pageable pageable, Integer customerId) throws IOException;
    public boolean checkin(String reservationId);
    public boolean checkout(String reservationId);
    public SourceReservation confirm(Integer customerId) throws IOException, ParseException;
}
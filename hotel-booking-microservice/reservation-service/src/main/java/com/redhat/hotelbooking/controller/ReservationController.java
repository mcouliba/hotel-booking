package com.redhat.hotelbooking.controller;

import com.redhat.hotelbooking.bean.Reservation;
import com.redhat.hotelbooking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
//specifying endpoint location
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     the getAll method retrieves all food items in the database. This is mapped to hte GET rest action
     @return A List() of Reservation items
     **/
    @RequestMapping(method=RequestMethod.GET, value="/findbycustomerid")
    public Page<Reservation> findByCustomerID(@RequestParam("customerid") Integer customerid, Pageable pageable) throws IOException {
        return reservationService.findByCustomerId(pageable, customerid);
    }

    @RequestMapping(method=RequestMethod.GET, value="/checkin")
    public boolean checkin(@RequestParam("reservationid") Integer reservationid) throws IOException {
        return reservationService.checkin(reservationid);
    }

    @RequestMapping(method=RequestMethod.GET, value="/checkout")
    public boolean checkout(@RequestParam("reservationid") Integer reservationid) throws IOException {
        return reservationService.checkout(reservationid);
    }
}
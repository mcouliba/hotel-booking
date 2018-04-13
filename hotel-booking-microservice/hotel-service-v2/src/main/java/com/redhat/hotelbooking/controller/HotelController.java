package com.redhat.hotelbooking.controller;

import com.redhat.hotelbooking.bean.Hotel;
import com.redhat.hotelbooking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
//specifying endpoint location
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    /**
     the getAll method retrieves all food items in the database. This is mapped to hte GET rest action
     @return A List() of Hotel items
     **/
    @RequestMapping(method=RequestMethod.GET)
    public Page<Hotel> list(Pageable pageable) {
        return hotelService.listAllByPage(pageable);
    }

    /**
     the getAll method retrieves all food items in the database. This is mapped to hte GET rest action
     @return A List() of Hotel items
     **/
    @RequestMapping(method=RequestMethod.GET, value="/findbyuserid")
    public Page<Hotel> findByUserID(@RequestParam("userid") String userid, Pageable pageable) throws IOException {
        return hotelService.findByUserID(pageable, userid);
    }
}
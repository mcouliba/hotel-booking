package com.redhat.hotelbooking.inventory.controller;

import com.redhat.hotelbooking.inventory.bean.Room;
import com.redhat.hotelbooking.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
//specifying endpoint location
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /**
     the getAll method retrieves all food items in the database. This is mapped to hte GET rest action
     @return A List() of Hotel items
     **/
    @RequestMapping(method=RequestMethod.GET, value="/room")
    public Page<Room> listRooms(Pageable pageable) {
        return inventoryService.listRooms(pageable);
    }

    /**
     the getAll method retrieves all food items in the database. This is mapped to hte GET rest action
     @return A List() of Room items
     **/
    @RequestMapping(method=RequestMethod.GET, value="/room/findbyhotel")
    public Page<Room> findRoomByHotel(Pageable pageable, @RequestParam("hotelid") Integer hotelId) {
        return inventoryService.findRoomByHotel(pageable, hotelId);
    }
}
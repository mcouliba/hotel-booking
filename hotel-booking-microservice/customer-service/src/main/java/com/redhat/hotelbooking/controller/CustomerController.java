package com.redhat.hotelbooking.controller;

import com.redhat.hotelbooking.bean.Authentication;
import com.redhat.hotelbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
//specifying endpoint location
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     the getAll method retrieves all food items in the database. This is mapped to hte GET rest action
     @return A List() of Authentication items
     **/
    @RequestMapping(method=RequestMethod.GET, value="/authenticate")
    public Authentication authenticate(@RequestParam("email") String email) throws IOException {
        return customerService.authenticate(email);
    }
}
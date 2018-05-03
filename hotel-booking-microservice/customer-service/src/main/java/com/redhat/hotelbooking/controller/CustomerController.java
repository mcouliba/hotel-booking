package com.redhat.hotelbooking.controller;

import com.redhat.hotelbooking.bean.Authentication;
import com.redhat.hotelbooking.bean.Details;
import com.redhat.hotelbooking.service.AuthenticationService;
import com.redhat.hotelbooking.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
//specifying endpoint location
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private DetailsService detailsService;

    /**
     the getAll method retrieves all food items in the database. This is mapped to hte GET rest action
     @return A List() of Authentication items
     **/
    @RequestMapping(method=RequestMethod.GET, value="/authenticate")
    public Authentication authenticate(@RequestParam("email") String email) throws IOException {
        return authenticationService.authenticate(email);
    }

    /**
     the getAll method retrieves all food items in the database. This is mapped to hte GET rest action
     @return A List() of Authentication items
     **/
    @RequestMapping(method=RequestMethod.GET, value="/details")
    public Details getDetails(@RequestParam("customerid") Integer customerid) throws IOException {
        return detailsService.get(customerid);
    }
}
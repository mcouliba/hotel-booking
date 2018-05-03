package com.redhat.hotelbooking.service;

import com.redhat.hotelbooking.bean.Details;

public interface DetailsService {
    public Details get(Integer customerid);
}
package com.redhat.hotelbooking.service;

import com.redhat.hotelbooking.bean.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface CustomerService {
    public Authentication authenticate(String email);
}
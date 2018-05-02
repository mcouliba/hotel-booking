package com.redhat.hotelbooking.service;

import com.redhat.hotelbooking.bean.Authentication;
import com.redhat.hotelbooking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Authentication authenticate(String email) {
		return customerRepository.findByEmail(email);
	}
}

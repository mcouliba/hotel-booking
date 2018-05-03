package com.redhat.hotelbooking.service;

import com.redhat.hotelbooking.bean.Authentication;
import com.redhat.hotelbooking.bean.Details;
import com.redhat.hotelbooking.repository.AuthenticationRepository;
import com.redhat.hotelbooking.repository.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class DetailsServiceImpl implements DetailsService {

	@Autowired
	private DetailsRepository detailsRepository;

	@Override
	public Details get(Integer customerid) {
		return detailsRepository.findOne(customerid);
	}
}

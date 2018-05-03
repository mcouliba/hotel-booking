package com.redhat.hotelbooking.service;

import com.redhat.hotelbooking.bean.Authentication;
import com.redhat.hotelbooking.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Override
	public Authentication authenticate(String email) {
		return authenticationRepository.findByEmail(email);
	}
}

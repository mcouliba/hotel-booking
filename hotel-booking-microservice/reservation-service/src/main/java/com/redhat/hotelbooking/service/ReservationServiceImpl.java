package com.redhat.hotelbooking.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.hotelbooking.bean.Reservation;
import com.redhat.hotelbooking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@Transactional
class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public Page<Reservation> findByCustomerId(Pageable pageable, Integer customerId) throws IOException {
		return reservationRepository.findByCustomerId(pageable, customerId);
	}

	@Override
	public boolean checkin(Integer reservationId){
		Reservation reservation2Update = reservationRepository.findOne(reservationId);
		reservation2Update.setStatus(ReservationService.CHECKIN_STATUS);
		reservationRepository.save(reservation2Update);
		return true;
	}

    @Override
    public boolean checkout(Integer reservationId){
        Reservation reservation2Update = reservationRepository.findOne(reservationId);
        reservation2Update.setStatus(ReservationService.CHECKOUT_STATUS);
        reservationRepository.save(reservation2Update);
        return true;
    }
}

package com.redhat.hotelbooking.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.hotelbooking.bean.Room;
import com.redhat.hotelbooking.repository.RoomRepository;
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
class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Value("${BOOKING_STATE_SERVICE_GET_URL:http://booking-state-service:8080/getbookingstate}")
	private String BOOKING_STATE_SERVICE_GET_URL;

	@Override
	public Page<Room> findByUserID(Pageable pageable, String userid) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response
				= restTemplate.getForEntity(BOOKING_STATE_SERVICE_GET_URL + "/" + userid, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode hotel_id = root.path("selection").path("hotel").path("id");
		return roomRepository.findByHotelId(pageable, hotel_id.asInt());
	}
}

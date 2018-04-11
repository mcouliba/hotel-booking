package com.redhat.hotelbooking.inventory.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.redhat.hotelbooking.inventory.bean.Room;
import com.redhat.hotelbooking.inventory.controller.InventoryController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryApplicationTest {

	@Autowired
	InventoryController controller;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testDefaultRoomList() {
		PageRequest request = new PageRequest(0,10);

		Page<Room> roomList = controller.listRooms(request);
		assertTrue(roomList!=null);
		assertEquals(10, roomList.getSize());
		assertEquals(3938, roomList.getContent().get(0).getRoomNumber().intValue());
	}

}
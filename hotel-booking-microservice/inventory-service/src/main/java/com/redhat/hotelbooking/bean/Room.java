package com.redhat.hotelbooking.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
/**
The hotel object will be stored in a table with the name "hotel"
**/
@Table(name="room", schema="DataServiceLayer")
/**
The Hotel class defines a hotel object for storage in a relational database system.
**/
public class Room {

    @Id
    private Integer id;
    @Column(name="hotel_id")
    private Integer hotelId;
    private String room_number;
    private Integer floor;
    private Double rate;
    private boolean living_area;
    private boolean microwave;
    private Integer num_adjoining_rooms;
    private Integer num_double_beds;
    private Integer num_king_beds;
    private Integer num_pets;
    private Integer num_pullouts;
    private Integer num_queen_beds;
    private boolean refrigerator;
    private boolean smoking;

    protected Room(){}
    /**
    This is the constructor for the database object.
    **/
    public Room(Integer id, Integer hotelId, String room_number, Integer floor,
                Double rate, boolean living_area, boolean microwave, Integer num_adjoining_rooms, Integer num_double_beds,
                Integer num_king_beds, Integer num_pets, Integer num_pullouts, Integer num_queen_beds,
                boolean refrigerator, boolean smoking) {
        this.id = id;
        this.hotelId = hotelId;
        this.room_number = room_number;
        this.floor = floor;
        this.rate = rate;
        this.living_area = living_area;
        this.microwave = microwave;
        this.num_adjoining_rooms = num_adjoining_rooms;
        this.num_double_beds = num_double_beds;
        this.num_king_beds = num_king_beds;
        this.num_pets = num_pets;
        this.num_pullouts = num_pullouts;
        this.num_queen_beds = num_queen_beds;
        this.refrigerator = refrigerator;
        this.smoking = smoking;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public boolean isLiving_area() {
        return living_area;
    }

    public void setLiving_area(boolean living_area) {
        this.living_area = living_area;
    }

    public boolean isMicrowave() {
        return microwave;
    }

    public void setMicrowave(boolean microwave) {
        this.microwave = microwave;
    }

    public Integer getNum_adjoining_rooms() {
        return num_adjoining_rooms;
    }

    public void setNum_adjoining_rooms(Integer num_adjoining_rooms) {
        this.num_adjoining_rooms = num_adjoining_rooms;
    }

    public Integer getNum_double_beds() {
        return num_double_beds;
    }

    public void setNum_double_beds(Integer num_double_beds) {
        this.num_double_beds = num_double_beds;
    }

    public Integer getNum_king_beds() {
        return num_king_beds;
    }

    public void setNum_king_beds(Integer num_king_beds) {
        this.num_king_beds = num_king_beds;
    }

    public Integer getNum_pets() {
        return num_pets;
    }

    public void setNum_pets(Integer num_pets) {
        this.num_pets = num_pets;
    }

    public Integer getNum_pullouts() {
        return num_pullouts;
    }

    public void setNum_pullouts(Integer num_pullouts) {
        this.num_pullouts = num_pullouts;
    }

    public Integer getNum_queen_beds() {
        return num_queen_beds;
    }

    public void setNum_queen_beds(Integer num_queen_beds) {
        this.num_queen_beds = num_queen_beds;
    }

    public boolean isRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(boolean refrigerator) {
        this.refrigerator = refrigerator;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }
}

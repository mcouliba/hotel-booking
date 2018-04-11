package com.redhat.hotelbooking.inventory.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
/**
The hotel object will be stored in a table with the name "hotel"
**/
@Table(name="room", schema="DataServiceLayer")
/**
The Room class defines a hotel object for storage in a relational database system.
**/
public class Room {

    @Id
    private Integer id;
    private String description;
    private String type;
    private Integer roomNumber;
    private Integer maxCapacity;
    private Integer hotelId;

    protected Room(){}

    /**
    This is the constructor for the database object.
    **/
    public Room(Integer id, String description, String type, Integer roomNumber, Integer maxCapacity,
                Integer hotelId) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.roomNumber = roomNumber;
        this.maxCapacity = maxCapacity;
        this.hotelId = hotelId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }
}

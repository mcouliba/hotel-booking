package com.redhat.hotelbooking.bean;

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
@Table(name="hotel", schema="DataServiceLayer")
/**
The Hotel class defines a hotel object for storage in a relational database system.
**/
public class Hotel {

    @Id
    private Integer id;
    private String name;
    private Double stars;
    private Integer price;
    private String cityName;
    private String countryName;
    private String address;
    private Integer available_rooms;

    protected Hotel(){}
    /**
    This is the constructor for the database object.
    **/
    public Hotel(Integer id, String name, Double stars, Integer price,
                 String cityName, String countryName, String address, Integer available_rooms) {
        this.id = id;
        this.name = name;
        this.stars = stars;
        this.price = price;
        this.cityName = cityName;
        this.countryName = countryName;
        this.address = address;
        this.available_rooms = available_rooms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCity_name() {
        return cityName;
    }

    public void setCity_name(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry_name() {
        return countryName;
    }

    public void setCountry_name(String countryName) {
        this.countryName = countryName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAvailable_rooms() {
        return available_rooms;
    }

    public void setAvailable_rooms(Integer available_rooms) {
        this.available_rooms = available_rooms;
    }
}

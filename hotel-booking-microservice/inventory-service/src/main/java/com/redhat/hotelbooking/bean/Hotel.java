package com.redhat.hotelbooking.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
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
    private String email;
    private String url;
    private String address;
    private String city;
    private String postal_code;
    private String country;

    protected Hotel(){}
    /**
    This is the constructor for the database object.
    **/
    public Hotel(Integer id, String name, Double stars, String email,
                 String url, String address, String city, String postal_code, String country) {
        this.id = id;
        this.name = name;
        this.stars = stars;
        this.email = email;
        this.url = url;
        this.address = address;
        this.city = city;
        this.postal_code = postal_code;
        this.country = country;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

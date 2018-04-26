package com.redhat.hotelbooking.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Entity
/**
The hotel object will be stored in a table with the name "hotel"
**/
@Table(name="reservation", schema="DataServiceLayer")
/**
The Reservation class defines a hotel object for storage in a relational database system.
**/
public class Reservation {

    @Id
    private Integer id;

    @Column(name="customer_id")
    private Integer customerId;
    private String hotel_name;
    private String hotel_city;
    private String hotel_country;
    private Date checkin;
    private Date checkout;
    private String status;

    protected Reservation(){}
    /**
    This is the constructor for the database object.
    **/
    public Reservation(Integer id, Integer customerId, String hotel_name, String hotel_city, String hotel_country,
                       Date checkin, Date checkout, String status) {
        this.id = id;
        this.customerId = customerId;
        this.hotel_name = hotel_name;
        this.hotel_city = hotel_city;
        this.hotel_country = hotel_country;
        this.checkin = checkin;
        this.checkout = checkout;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_city() {
        return hotel_city;
    }

    public void setHotel_city(String hotel_city) {
        this.hotel_city = hotel_city;
    }

    public String getHotel_country() {
        return hotel_country;
    }

    public void setHotel_country(String hotel_country) {
        this.hotel_country = hotel_country;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.redhat.hotelbooking.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
/**
The hotel object will be stored in a table with the name "hotel"
**/
@Table(name="sourceReservation", schema="DataServiceLayer")
/**
The Reservation class defines a hotel object for storage in a relational database system.
**/
public class SourceReservation {

    @Id
    @GeneratedValue(generator = "teiid-uuid")
    @GenericGenerator(name = "teiid-uuid", strategy = "uuid")
    private String id;
    private Integer customerId;
    private Integer roomId;
    private Date checkin;
    private Date checkout;
    private Double dailyRate;
    private String status;

    protected SourceReservation(){}
    /**
    This is the constructor for the database object.
    **/
    public SourceReservation(String id, Integer customerId, Integer roomId,
                             Date checkin, Date checkout, Double dailyRate, String status) {
        this.id = id;
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkin = checkin;
        this.checkout = checkout;
        this.dailyRate = dailyRate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
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

    public Double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(Double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

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
@Table(name="customer", schema="DataServiceLayer")
/**
The Authentication class defines a hotel object for storage in a relational database system.
**/
public class Authentication {

    @Id
    private Integer id;
    private String email;
    private String country;

    protected Authentication(){}
    /**
    This is the constructor for the database object.
    **/
    public Authentication(Integer id, String email, String country) {
        this.id = id;
        this.email = email;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

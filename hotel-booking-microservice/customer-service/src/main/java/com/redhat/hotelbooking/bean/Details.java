package com.redhat.hotelbooking.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
/**
The hotel object will be stored in a table with the name "hotel"
**/
@Table(name="details", schema="DataServiceLayer")
/**
The Authentication class defines a hotel object for storage in a relational database system.
**/
public class Details {

    @Id
    private Integer id;
    private String fullname;
    private String email;
    private String address;
    private Date member_since;
    private String rewards_id;
    private String credit_card_number;
    private String credit_card_type;
    private String expiration_date;

    protected Details(){}
    /**
    This is the constructor for the database object.
    **/
    public Details(Integer id, String fullname, String email, String address, Date member_since, String rewards_id
            , String credit_card_number, String credit_card_type, String expiration_date) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.member_since = member_since;
        this.rewards_id = rewards_id;
        this.credit_card_number = credit_card_number;
        this.credit_card_type = credit_card_type;
        this.expiration_date = expiration_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getMember_since() {
        return member_since;
    }

    public void setMember_since(Date member_since) {
        this.member_since = member_since;
    }

    public String getRewards_id() {
        return rewards_id;
    }

    public void setRewards_id(String rewards_id) {
        this.rewards_id = rewards_id;
    }

    public String getCredit_card_number() {
        return credit_card_number;
    }

    public void setCredit_card_number(String credit_card_number) {
        this.credit_card_number = credit_card_number;
    }

    public String getCredit_card_type() {
        return credit_card_type;
    }

    public void setCredit_card_type(String credit_card_type) {
        this.credit_card_type = credit_card_type;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }
}

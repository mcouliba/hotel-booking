package com.redhat.hotelbooking.datagen.domain;

public final class Hotel {

    private final String addressLine1;
    private final String city;
    private final int countryId;
    private final String email;
    private final int hotelChainId;
    private final int id;
    private final String postalCode;
    private final String url;

    public Hotel( final int id,
                  final int hotelChainId,
                  final int countryId,
                  final String addressLine1,
                  final String city,
                  final String postalCode,
                  final String email,
                  final String url ) {
        this.addressLine1 = addressLine1;
        this.city = city;
        this.countryId = countryId;
        this.email = email;
        this.hotelChainId = hotelChainId;
        this.id = id;
        this.postalCode = postalCode;
        this.url = url;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public String getCity() {
        return this.city;
    }

    public int getCountryId() {
        return this.countryId;
    }

    public String getEmail() {
        return this.email;
    }

    public int getHotelChainId() {
        return this.hotelChainId;
    }

    public int getId() {
        return this.id;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getUrl() {
        return this.url;
    }

}

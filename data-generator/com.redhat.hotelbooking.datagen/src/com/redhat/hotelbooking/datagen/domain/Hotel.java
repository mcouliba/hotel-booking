package com.redhat.hotelbooking.datagen.domain;

public final class Hotel {

    private final String addressLine1;
    private final int cityId;
    private final String email;
    private final int hotelChainId;
    private final int id;
    private final String name;
    private final String url;

    public Hotel( final int id,
                  final int hotelChainId,
                  final String name,
                  final String addressLine1,
                  final int cityId,
                  final String email,
                  final String url ) {
        this.addressLine1 = addressLine1;
        this.cityId = cityId;
        this.email = email;
        this.hotelChainId = hotelChainId;
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public int getCityId() {
        return this.cityId;
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

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

}

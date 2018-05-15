/*
 * JBoss, Home of Professional Open Source.
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */
package com.redhat.hotelbooking.datagen.domain;

public final class Room {

    private final int configId;
    private final int floor;
    private final int hotelId;
    private final int id;
    private final double rate;
    private final String roomNumber;

    public Room( final int id,
                 final int hotelId,
                 final int configId,
                 final double rate,
                 final int floor,
                 final String roomNumber ) {
        this.configId = configId;
        this.rate = rate;
        this.floor = floor;
        this.hotelId = hotelId;
        this.id = id;
        this.roomNumber = roomNumber;
    }

    public int getConfig() {
        return this.configId;
    }

    public int getFloor() {
        return this.floor;
    }

    public int getHotelId() {
        return this.hotelId;
    }

    public int getId() {
        return this.id;
    }

    public double getRate() {
        return this.rate;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

}

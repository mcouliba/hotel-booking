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

public final class RoomConfig {

    private final int id;
    private final boolean livingSpace;
    private final boolean microwave;
    private final int numAdjoiningRooms;
    private final int numDouble;
    private final int numKing;
    private final int numPets;
    private final int numPullouts;
    private final int numQueen;
    private final boolean refrigerator;
    private final boolean smoking;

    public RoomConfig( final int id,
                       final boolean livingSpace,
                       final boolean microwave,
                       final int numAdjoiningRooms,
                       final int numDouble,
                       final int numKing,
                       final int numPets,
                       final int numPullouts,
                       final int numQueen,
                       final boolean refrigerator,
                       final boolean smoking ) {
        this.numAdjoiningRooms = numAdjoiningRooms;
        this.id = id;
        this.livingSpace = livingSpace;
        this.microwave = microwave;
        this.numDouble = numDouble;
        this.numKing = numKing;
        this.numPets = numPets;
        this.numPullouts = numPullouts;
        this.numQueen = numQueen;
        this.refrigerator = refrigerator;
        this.smoking = smoking;
    }

    @Override
    public boolean equals( final Object obj ) {
        if ( obj == null || !( obj instanceof RoomConfig ) ) {
            return false;
        }

        final RoomConfig that = ( RoomConfig )obj;

        return this.numAdjoiningRooms == that.numAdjoiningRooms
               && this.livingSpace == that.livingSpace
               && this.microwave == that.microwave
               && this.numDouble == that.numDouble
               && this.numKing == that.numKing
               && this.numPets == that.numPets
               && this.numPullouts == that.numPullouts
               && this.numQueen == that.numQueen
               && this.refrigerator == that.refrigerator
               && this.smoking == that.smoking;
    }

    public int getId() {
        return this.id;
    }

    public int getNumAdjoiningRooms() {
        return this.numAdjoiningRooms;
    }

    public int getNumDouble() {
        return this.numDouble;
    }

    public int getNumKing() {
        return this.numKing;
    }

    public int getNumPets() {
        return this.numPets;
    }

    public int getNumPullouts() {
        return this.numPullouts;
    }

    public int getNumQueen() {
        return this.numQueen;
    }

    public boolean hasLivingSpace() {
        return this.livingSpace;
    }

    public boolean hasMicrowave() {
        return this.microwave;
    }

    public boolean hasRefrigerator() {
        return this.refrigerator;
    }

    public boolean isSmoking() {
        return this.smoking;
    }

}

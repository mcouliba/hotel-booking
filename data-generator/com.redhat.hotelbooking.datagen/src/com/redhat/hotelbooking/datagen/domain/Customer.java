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

import java.util.Date;

public final class Customer {

    private final String addressLine1;
    private final String city;
    private final String email;
    private final int id;
    private final Date memberSince;
    private final String name;
    private final String postalCode;
    private final String rewardsId;

    public Customer( final int id,
                     final String name,
                     final String email,
                     final String addressLine1,
                     final String city,
                     final String postalCode,
                     final Date memberSince,
                     final String rewardsId ) {
        this.addressLine1 = addressLine1;
        this.city = city;
        this.email = email;
        this.id = id;
        this.memberSince = memberSince;
        this.name = name;
        this.postalCode = postalCode;
        this.rewardsId = rewardsId;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public String getCity() {
        return this.city;
    }

    public String getEmail() {
        return this.email;
    }

    public int getId() {
        return this.id;
    }

    public Date getMemberSince() {
        return this.memberSince;
    }

    public String getName() {
        return this.name;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getRewardsId() {
        return this.rewardsId;
    }

}

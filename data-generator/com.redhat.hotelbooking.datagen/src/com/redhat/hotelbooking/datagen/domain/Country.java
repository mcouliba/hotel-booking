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

import java.util.Arrays;
import java.util.List;

public final class Country {

    public static final Country AUSTRALIA = new Country( 101, "Australia", "AUS", "AUD" );
    public static final Country CANADA = new Country( 102, "Canada", "CAN", "CAD" );
    public static final Country CHINA = new Country( 103, "China", "CHN", "CNY" );
    public static final Country CZECH_REPUBLIC = new Country( 104, "Czech Republic", "CZE", "CZK" );
    public static final Country FRANCE = new Country( 105, "France", "FRA", "FRF" );
    public static final Country GERMANY = new Country( 106, "Germany", "DEU", "DEM" );
    public static final Country JAPAN = new Country( 107, "Japan", "JPN", "JPY" );
    public static final Country PERU = new Country( 108, "Peru", "PER", "PEN" );
    public static final Country SPAIN = new Country( 109, "Spain", "ESP", "ESP" );
    public static final Country UNITED_STATES = new Country( 110, "United States", "USA", "USD" );

    public static final List< Country > COUNTRIES = Arrays.asList(
        new Country[] {
            AUSTRALIA,
            CANADA,
            CHINA,
            CZECH_REPUBLIC,
            FRANCE,
            GERMANY,
            JAPAN,
            PERU,
            SPAIN,
            UNITED_STATES
        }
    );

    private final int id;
    private final String isoCountryCode;
    private final String isoCurrencyCode;
    private final String name;

    public Country( final int id,
                    final String name,
                    final String isoCountryCode,
                    final String isoCurrencyCode ) {
        this.id = id;
        this.name = name;
        this.isoCountryCode = isoCountryCode;
        this.isoCurrencyCode = isoCurrencyCode;
    }

    public int getId() {
        return this.id;
    }

    public String getIsoCountryCode() {
        return this.isoCountryCode;
    }

    public String getIsoCurrencyCode() {
        return this.isoCurrencyCode;
    }

    public String getName() {
        return this.name;
    }

}

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

public final class City {

    private static int cityId = 200;

    public static final List< City > CITIES = Arrays.asList(
        new City[] { new City( cityId++, Country.AUSTRALIA.getId(), "Sydney", "2055" ),
                     new City( cityId++, Country.AUSTRALIA.getId(), "Melbourne", "3005" ),
                     new City( cityId++, Country.AUSTRALIA.getId(), "Brisbane", "4008" ),
                     new City( cityId++, Country.CANADA.getId(), "Toronto", "MC3" ),
                     new City( cityId++, Country.CANADA.getId(), "Montreal", "H1B" ),
                     new City( cityId++, Country.CANADA.getId(), "Calgary", "T1Y" ),
                     new City( cityId++, Country.CHINA.getId(), "Shanghai", "200009" ),
                     new City( cityId++, Country.CHINA.getId(), "Guangzhou", "510015" ),
                     new City( cityId++, Country.CHINA.getId(), "Beijing", "065001" ),
                     new City( cityId++, Country.CZECH_REPUBLIC.getId(), "Prague", "100 00" ),
                     new City( cityId++, Country.CZECH_REPUBLIC.getId(), "Brno", "649 99" ),
                     new City( cityId++, Country.CZECH_REPUBLIC.getId(), "Ostrava", "729 99" ),
                     new City( cityId++, Country.FRANCE.getId(), "Paris", "75004" ),
                     new City( cityId++, Country.FRANCE.getId(), "Marseille", "13004" ),
                     new City( cityId++, Country.FRANCE.getId(), "Lyon", "69003" ),
                     new City( cityId++, Country.FRANCE.getId(), "Toulouse", "31043" ),
                     new City( cityId++, Country.GERMANY.getId(), "Berlin", "10178" ),
                     new City( cityId++, Country.GERMANY.getId(), "Hamburg", "20146" ),
                     new City( cityId++, Country.GERMANY.getId(), "Munich", "80337" ),
                     new City( cityId++, Country.JAPAN.getId(), "Tokyo", "100-005" ),
                     new City( cityId++, Country.JAPAN.getId(), "Kyoto", "520-0465" ),
                     new City( cityId++, Country.JAPAN.getId(), "Nagoya", "450-6002" ),
                     new City( cityId++, Country.PERU.getId(), "Lima", "07021" ),
                     new City( cityId++, Country.PERU.getId(), "Arequipa", "04000" ),
                     new City( cityId++, Country.PERU.getId(), "Callao", "15112" ),
                     new City( cityId++, Country.SPAIN.getId(), "Madrid", "28005" ),
                     new City( cityId++, Country.SPAIN.getId(), "Barcelona", "08009" ),
                     new City( cityId++, Country.SPAIN.getId(), "Valencia", "46003" ),
                     new City( cityId++, Country.UNITED_STATES.getId(), "New York City", "10010" ),
                     new City( cityId++, Country.UNITED_STATES.getId(), "Los Angeles", "90024" ),
                     new City( cityId++, Country.UNITED_STATES.getId(), "Chicago", "60608" ),
                     new City( cityId++, Country.UNITED_STATES.getId(), "Houston", "77016" ),
                     new City( cityId++, Country.UNITED_STATES.getId(), "Philadelphia", "19019" ),
        }
    );

    private final int countryId;
    private final int id;
    private final String name;
    private final String postalCode;

    public City( final int id,
                 final int countryId,
                 final String name,
                 final String postalCode ) {
        this.id = id;
        this.countryId = countryId;
        this.name = name;
        this.postalCode = postalCode;
    }

    public int getCountryId() {
        return this.countryId;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

}
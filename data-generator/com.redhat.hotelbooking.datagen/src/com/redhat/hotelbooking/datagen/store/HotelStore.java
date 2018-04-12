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
package com.redhat.hotelbooking.datagen.store;

import java.util.List;

import com.redhat.hotelbooking.datagen.domain.Hotel;

public final class HotelStore implements DomainStore {

    public enum Column {

        ADDRESS_LINE_1,
        CITY,
        COUNTRY_ID,
        EMAIL,
        HOTEL_CHAIN_ID,
        ID,
        POSTAL_CODE,
        URL

    }

    public static final String TABLE_NAME = "HOTEL";
    public static final String COUNTRY_FK_NAME = "COUNTRY_FK";
    public static final String HOTEL_CHAIN_FK_NAME = "HOTEL_CHAIN_FK";

    // @formatter:off

    private static final String COLUMNS = "\"" + Column.ID + "\", "
                                          + '"' + Column.HOTEL_CHAIN_ID + "\", "
                                          + '"' + Column.COUNTRY_ID + "\", "
                                          + '"' + Column.ADDRESS_LINE_1 + "\", "
                                          + '"' + Column.CITY + "\", "
                                          + '"' + Column.POSTAL_CODE + "\", "
                                          + '"' + Column.EMAIL + "\", "
                                          + '"' + Column.URL + '\"';

    private static final String CREATE_TABLE_STMT
        = "CREATE TABLE \"" + TABLE_NAME + "\" (\n"
          + "\t\"" + Column.ID + "\" INTEGER NOT NULL,\n"
          + "\t\"" + Column.HOTEL_CHAIN_ID + "\" INTEGER NOT NULL,\n"
          + "\t\"" + Column.COUNTRY_ID + "\" INTEGER NOT NULL,\n"
          + "\t\"" + Column.ADDRESS_LINE_1 + "\" STRING NOT NULL,\n"
          + "\t\"" + Column.CITY + "\" STRING NOT NULL,\n"
          + "\t\"" + Column.POSTAL_CODE + "\" STRING NOT NULL,\n"
          + "\t\"" + Column.EMAIL + "\" STRING NOT NULL,\n"
          + "\t\"" + Column.URL + "\" STRING NOT NULL,\n"
          + "\tPRIMARY KEY ( \"" + Column.ID + "\" ),\n"
          + "\t" + DomainStore.createForeignKeyConstraint( HOTEL_CHAIN_FK_NAME,
                                                           Column.HOTEL_CHAIN_ID.toString(),
                                                           HotelChainStore.TABLE_NAME,
                                                           HotelChainStore.Column.ID.toString() ) + ",\n"
          + "\t" + DomainStore.createForeignKeyConstraint( COUNTRY_FK_NAME,
                                                           Column.COUNTRY_ID.toString(),
                                                           CountryStore.TABLE_NAME,
                                                           CountryStore.Column.ID.toString() ) + "\n"
          + ");";

    private static final String INSERT_STMT = "INSERT INTO \""
                                              + TABLE_NAME
                                              + "\" ( "
                                              + COLUMNS
                                              + " ) "
                                              + DomainStore.createValuesStatement( Column.values().length );

    // @formatter:on

    public HotelStore() {
        // nothing to do
    }

    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getDropStatement() {
        return getDropStatement( TABLE_NAME );
    }

    public String getInsertStatements( final List< Hotel > hotels ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final Hotel hotel : hotels ) {
            // @formatter:off
            final String insertStmt = String.format( INSERT_STMT,
                                                     toDdl( hotel.getId() ),
                                                     toDdl( hotel.getHotelChainId() ),
                                                     toDdl( hotel.getCountryId() ),
                                                     toDdl( hotel.getAddressLine1() ),
                                                     toDdl( hotel.getCity() ),
                                                     toDdl( hotel.getPostalCode() ),
                                                     toDdl( hotel.getEmail() ),
                                                     toDdl( hotel.getUrl() ) );
            // @formatter:on
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

}

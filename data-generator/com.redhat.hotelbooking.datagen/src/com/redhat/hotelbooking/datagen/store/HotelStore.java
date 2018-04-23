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
        CITY_ID,
        EMAIL,
        HOTEL_CHAIN_ID,
        ID,
        NAME,
        STARS,
        URL;

        public String toCreateStatement() {
            final StringBuilder builder = new StringBuilder();
            builder.append( DomainStore.addQuotes( this.toString() ) ).append( " " );

            switch ( this ) {
                case ADDRESS_LINE_1:
                    builder.append( "CHARACTER VARYING(256) NOT NULL" );
                    break;
                case CITY_ID:
                    builder.append( "INTEGER NOT NULL" );
                    break;
                case EMAIL:
                    builder.append( "CHARACTER VARYING(256) NOT NULL" );
                    break;
                case HOTEL_CHAIN_ID:
                    builder.append( "INTEGER NOT NULL" );
                    break;
                case ID:
                    builder.append( "INTEGER NOT NULL" );
                    break;
                case NAME:
                    builder.append( "CHARACTER VARYING(256) NOT NULL" );
                    break;
                case STARS:
                    builder.append( "DECIMAL(3,2) NOT NULL" );
                    break;
                case URL:
                    builder.append( "CHARACTER VARYING(256) NOT NULL" );
                    break;
                default:
                    throw new RuntimeException();
            }

            return builder.toString();
        }

    }

    public static final String TABLE_NAME = "HOTEL";
    public static final String CITY_FK_NAME = "CITY_FK";
    public static final String HOTEL_CHAIN_FK_NAME = "HOTEL_CHAIN_FK";

    private static final String COLUMNS = DomainStore.toColumnsStatement( Column.ID.name(),
                                                                          Column.HOTEL_CHAIN_ID.name(),
                                                                          Column.NAME.name(),
                                                                          Column.ADDRESS_LINE_1.name(),
                                                                          Column.CITY_ID.name(),
                                                                          Column.EMAIL.name(),
                                                                          Column.STARS.name(),
                                                                          Column.URL.name() );

    private static final String CREATE_TABLE_STMT
        = "CREATE TABLE " + DomainStore.addQuotes( TABLE_NAME ) + " (\n"
          + "\t" + Column.ID.toCreateStatement() + ",\n"
          + "\t" + Column.HOTEL_CHAIN_ID.toCreateStatement() + ",\n"
          + "\t" + Column.NAME.toCreateStatement() + ",\n"
          + "\t" + Column.ADDRESS_LINE_1.toCreateStatement() + ",\n"
          + "\t" + Column.CITY_ID.toCreateStatement() + ",\n"
          + "\t" + Column.EMAIL.toCreateStatement() + ",\n"
          + "\t" + Column.STARS.toCreateStatement() + ",\n"
          + "\t" + Column.URL.toCreateStatement() + ",\n"
          + "\tPRIMARY KEY ( " + DomainStore.addQuotes( Column.ID.name() ) + " ),\n"
          + "\t" + DomainStore.createForeignKeyConstraint( HOTEL_CHAIN_FK_NAME,
                                                           Column.HOTEL_CHAIN_ID.toString(),
                                                           HotelChainStore.TABLE_NAME,
                                                           HotelChainStore.Column.ID.toString() ) + ",\n"
          + "\t" + DomainStore.createForeignKeyConstraint( CITY_FK_NAME,
                                                           Column.CITY_ID.toString(),
                                                           CityStore.TABLE_NAME,
                                                           CityStore.Column.ID.toString() ) + "\n"
          + ");";

    private static final String INSERT_STMT = "INSERT INTO "
                                              + DomainStore.addQuotes( TABLE_NAME )
                                              + " ( "
                                              + COLUMNS
                                              + " ) "
                                              + DomainStore.createValuesStatement( Column.values().length );

    public HotelStore() {
        // nothing to do
    }

    @Override
    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getInsertStatements( final List< Hotel > hotels ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final Hotel hotel : hotels ) {
            final String insertStmt = String.format( INSERT_STMT,
                                                     toDdl( hotel.getId() ),
                                                     toDdl( hotel.getHotelChainId() ),
                                                     toDdl( hotel.getName() ),
                                                     toDdl( hotel.getAddressLine1() ),
                                                     toDdl( hotel.getCityId() ),
                                                     toDdl( hotel.getEmail() ),
                                                     toDdl( hotel.getStars() ),
                                                     toDdl( hotel.getUrl() ) );
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

    @Override
    public String getTableName() {
        return HotelStore.TABLE_NAME;
    }

}

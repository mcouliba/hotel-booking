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

import com.redhat.hotelbooking.datagen.domain.HotelChain;

public final class HotelChainStore implements DomainStore {

    public enum Column {

        ID,
        NAME

    }

    public static final String TABLE_NAME = "HOTEL_CHAIN";

    // @formatter:off

    private static final String COLUMNS = "\"" + Column.ID + "\", "
                                          + '"' + Column.NAME + '\"';

    private static final String CREATE_TABLE_STMT = "CREATE TABLE \"" + TABLE_NAME + "\" (\n"
                                                    + "\t\"" + Column.ID + "\" INTEGER NOT NULL,\n"
                                                    + "\t\"" + Column.NAME + "\" STRING NOT NULL,\n"
                                                    + "\tPRIMARY KEY ( \"" + Column.ID + "\" )\n"
                                                    + ");";

    private static final String INSERT_STMT = "INSERT INTO \""
                                              + TABLE_NAME
                                              + "\" ( "
                                              + COLUMNS
                                              + " ) VALUES ( %s, '%s' );";

    // @formatter:on

    public HotelChainStore() {
        // nothing to do
    }

    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getDropStatement() {
        return getDropStatement( TABLE_NAME );
    }

    public String getInsertStatements( final List< HotelChain > hotelChains ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final HotelChain hotelChain : hotelChains ) {
            // @formatter:off
            final String insertStmt = String.format( INSERT_STMT,
                                                     toDdl( hotelChain.getId() ),
                                                     toDdl( hotelChain.getName() ) );
            // @formatter:on
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

}

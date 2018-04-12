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

import com.redhat.hotelbooking.datagen.domain.Customer;

public final class CustomerStore implements DomainStore {

    public enum Column {

        ADDRESS_LINE_1,
        CITY,
        EMAIL,
        ID,
        MEMBER_SINCE,
        NAME,
        POSTAL_CODE,
        REWARDS_ID

    }

    public static final String TABLE_NAME = "CUSTOMER";

    // @formatter:off

    private static final String COLUMNS = "\"" + Column.ID + "\", "
                                          + '"' + Column.NAME + "\", "
                                          + '"' + Column.EMAIL + "\", "
                                          + '"' + Column.REWARDS_ID + "\", "
                                          + '"' + Column.MEMBER_SINCE + "\", "
                                          + '"' + Column.ADDRESS_LINE_1 + "\", "
                                          + '"' + Column.CITY + "\", "
                                          + '"' + Column.POSTAL_CODE + '\"';

    private static final String CREATE_TABLE_STMT = "CREATE TABLE \"" + TABLE_NAME + "\" (\n"
                                                    + "\t\"" + Column.ID + "\" INTEGER NOT NULL,\n"
                                                    + "\t\"" + Column.NAME + "\" STRING NOT NULL,\n"
                                                    + "\t\"" + Column.EMAIL + "\" STRING NOT NULL,\n"
                                                    + "\t\"" + Column.REWARDS_ID + "\" STRING NOT NULL,\n"
                                                    + "\t\"" + Column.MEMBER_SINCE + "\" DATE NOT NULL,\n"
                                                    + "\t\"" + Column.ADDRESS_LINE_1 + "\" STRING NOT NULL,\n"
                                                    + "\t\"" + Column.CITY + "\" STRING NOT NULL,\n"
                                                    + "\t\"" + Column.POSTAL_CODE + "\" STRING NOT NULL,\n"
                                                    + "\tPRIMARY KEY ( \"" + Column.ID + "\" )\n"
                                                    + ");";

    private static final String INSERT_STMT = "INSERT INTO \""
                                              + TABLE_NAME
                                              + "\" ( "
                                              + COLUMNS
                                              + " ) "
                                              + DomainStore.createValuesStatement( Column.values().length );

    // @formatter:on

    public CustomerStore() {
        // nothing to do
    }

    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getDropStatement() {
        return getDropStatement( TABLE_NAME );
    }

    public String getInsertStatements( final List< Customer > customers ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final Customer customer : customers ) {
            // @formatter:off
            final String insertStmt = String.format( INSERT_STMT,
                                                     toDdl( customer.getId() ),
                                                     toDdl( customer.getName() ),
                                                     toDdl( customer.getEmail() ),
                                                     toDdl( customer.getRewardsId() ),
                                                     toDdl( customer.getMemberSince() ),
                                                     toDdl( customer.getAddressLine1() ),
                                                     toDdl( customer.getCity() ),
                                                     toDdl( customer.getPostalCode() ) );
            // @formatter:on
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

}

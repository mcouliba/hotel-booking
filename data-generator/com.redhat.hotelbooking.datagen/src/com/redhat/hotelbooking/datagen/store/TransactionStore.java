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

import com.redhat.hotelbooking.datagen.domain.Country;

public final class TransactionStore implements DomainStore {

    public enum Column {

        AMOUNT,
        ID,
        PAYMENT_INFO_ID,
        RESERVATION_ID

    }

    public static final String TABLE_NAME = "TRANSACTION";
    public static final String PAYMENT_INFO_FK_NAME = "PAYMENT_INFO_FK";
    public static final String RESERVATION_FK_NAME = "RESERVATION_FK";

    // @formatter:off

    private static final String COLUMNS = "\"" + Column.ID + "\", "
                                          + '"' + Column.RESERVATION_ID + "\", "
                                          + '"' + Column.PAYMENT_INFO_ID + "\", "
                                          + '"' + Column.AMOUNT + '\"';

    private static final String CREATE_TABLE_STMT
        = "CREATE TABLE \"" + TABLE_NAME + "\" (\n"
          + "\t\"" + Column.ID + "\" INTEGER NOT NULL,\n"
          + "\t\"" + Column.RESERVATION_ID + "\" INTEGER NOT NULL,\n"
          + "\t\"" + Column.PAYMENT_INFO_ID + "\" INTEGER NOT NULL,\n"
          + "\t\"" + Column.AMOUNT + "\" DECIMAL(8,2) NOT NULL,\n"
          + "\tPRIMARY KEY ( \"" + Column.ID + "\" ),\n"
          + "\t" + DomainStore.createForeignKeyConstraint( RESERVATION_FK_NAME,
                                                           Column.RESERVATION_ID.toString(),
                                                           ReservationStore.TABLE_NAME,
                                                           ReservationStore.Column.ID.toString() ) + ",\n"
          + "\t" + DomainStore.createForeignKeyConstraint( PAYMENT_INFO_FK_NAME,
                                                           Column.PAYMENT_INFO_ID.toString(),
                                                           PaymentInfoStore.TABLE_NAME,
                                                           PaymentInfoStore.Column.ID.toString() ) + "\n"
                                                    + ");";

    private static final String INSERT_STMT = "INSERT INTO \""
                                              + TABLE_NAME
                                              + "\" ( "
                                              + COLUMNS
                                              + " ) "
                                              + DomainStore.createValuesStatement( Column.values().length );

    // @formatter:on

    public TransactionStore() {
        // nothing to do
    }

    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getDropStatement() {
        return getDropStatement( TABLE_NAME );
    }

    public String getInsertStatements( final List< Country > countries ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final Country country : countries ) {
            // @formatter:off
            final String insertStmt = String.format( INSERT_STMT,
                                                     toDdl( country.getId() ),
                                                     toDdl( country.getName() ),
                                                     toDdl( country.getIsoCountryCode() ),
                                                     toDdl( country.getIsoCurrencyCode() ) );
            // @formatter:on
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

}

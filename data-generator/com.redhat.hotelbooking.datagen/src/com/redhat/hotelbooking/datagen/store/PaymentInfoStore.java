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

import com.redhat.hotelbooking.datagen.domain.PaymentInfo;

public final class PaymentInfoStore implements DomainStore {

    public enum Column {

        CREDIT_CARD_NUMBER,
        CREDIT_CARD_TYPE,
        CUSTOMER_ID,
        EXPIRATION_DATE,
        ID,
        SECURITY_CODE

    }

    public static final String TABLE_NAME = "PAYMENT_INFO";
    public static final String CUSTOMER_FK_NAME = "CUSTOMER_FK";

    // @formatter:off

    private static final String COLUMNS = "\"" + Column.ID + "\", "
                                          + '"' + Column.CUSTOMER_ID + "\", "
                                          + '"' + Column.CREDIT_CARD_NUMBER + "\", "
                                          + '"' + Column.CREDIT_CARD_TYPE + "\", "
                                          + '"' + Column.EXPIRATION_DATE + "\", "
                                          + '"' + Column.SECURITY_CODE + '\"';

    private static final String CREATE_TABLE_STMT
        = "CREATE TABLE \"" + TABLE_NAME + "\" (\n"
          + "\t\"" + Column.ID + "\" INTEGER NOT NULL,\n"
          + "\t\"" + Column.CUSTOMER_ID + "\" INTEGER NOT NULL,\n"
          + "\t\"" + Column.CREDIT_CARD_NUMBER + "\" INTEGER NOT NULL,\n"
          + "\t\"" + Column.CREDIT_CARD_TYPE + "\" STRING NOT NULL,\n"
          + "\t\"" + Column.EXPIRATION_DATE + "\" DATE NOT NULL,\n"
          + "\t\"" + Column.SECURITY_CODE + "\" INTEGER NOT NULL,\n"
          + "\tPRIMARY KEY ( \"" + Column.ID + "\" ),\n"
          + "\t" + DomainStore.createForeignKeyConstraint( CUSTOMER_FK_NAME,
                                                           Column.CUSTOMER_ID.toString(),
                                                           CustomerStore.TABLE_NAME,
                                                           CustomerStore.Column.ID.toString() ) + "\n"
          + ");";

    private static final String INSERT_STMT = "INSERT INTO \""
                                              + TABLE_NAME
                                              + "\" ( "
                                              + COLUMNS
                                              + " ) "
                                              + DomainStore.createValuesStatement( Column.values().length );

    // @formatter:on

    public PaymentInfoStore() {
        // nothing to do
    }

    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getDropStatement() {
        return getDropStatement( TABLE_NAME );
    }

    public String getInsertStatements( final List< PaymentInfo > paymentInfos ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final PaymentInfo paymentInfo : paymentInfos ) {
            // @formatter:off
            final String insertStmt = String.format( INSERT_STMT,
                                                     toDdl( paymentInfo.getId() ),
                                                     toDdl( paymentInfo.getCustomerId() ),
                                                     toDdl( paymentInfo.getCreditCardNumber() ),
                                                     toDdl( paymentInfo.getCreditCardType() ),
                                                     toDdl( paymentInfo.getExpirationDate() ),
                                                     toDdl( paymentInfo.getSecurityCode() ) );
            // @formatter:on
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

}

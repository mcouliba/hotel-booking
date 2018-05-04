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
        SECURITY_CODE;

        public String getCreateStatementTypeDefinition() {
            switch ( this ) {
                case CREDIT_CARD_NUMBER:
                    return "CHARACTER VARYING(256) NOT NULL";
                case CREDIT_CARD_TYPE:
                    return "CHARACTER VARYING(256) NOT NULL";
                case CUSTOMER_ID:
                    return CustomerStore.Column.ID.getCreateStatementTypeDefinition();
                case EXPIRATION_DATE:
                    return "DATE NOT NULL";
                case ID:
                    return "INTEGER NOT NULL";
                case SECURITY_CODE:
                    return "INTEGER NOT NULL";
                default:
                    throw new RuntimeException();
            }
        }

        public String toCreateStatement() {
            return DomainStore.addQuotes( toString() ) + " " + getCreateStatementTypeDefinition();
        }

    }

    public static final String TABLE_NAME = "PAYMENT_INFO";
    public static final String CUSTOMER_FK_NAME = "CUSTOMER_FK";

    private static final String COLUMNS = DomainStore.toColumnsStatement( Column.ID.name(),
                                                                          Column.CUSTOMER_ID.name(),
                                                                          Column.CREDIT_CARD_NUMBER.name(),
                                                                          Column.CREDIT_CARD_TYPE.name(),
                                                                          Column.EXPIRATION_DATE.name(),
                                                                          Column.SECURITY_CODE.name() );

    private static final String CREATE_TABLE_STMT
        = "CREATE TABLE " + DomainStore.addQuotes( TABLE_NAME ) + " (\n"
          + "\t" + Column.ID.toCreateStatement() + ",\n"
          + "\t" + Column.CUSTOMER_ID.toCreateStatement() + ",\n"
          + "\t" + Column.CREDIT_CARD_NUMBER.toCreateStatement() + ",\n"
          + "\t" + Column.CREDIT_CARD_TYPE.toCreateStatement() + ",\n"
          + "\t" + Column.EXPIRATION_DATE.toCreateStatement() + ",\n"
          + "\t" + Column.SECURITY_CODE.toCreateStatement() + ",\n"
          + "\tPRIMARY KEY ( " + DomainStore.addQuotes( Column.ID.name() ) + " ),\n"
          + "\t" + DomainStore.createForeignKeyConstraint( CUSTOMER_FK_NAME,
                                                           Column.CUSTOMER_ID.toString(),
                                                           CustomerStore.TABLE_NAME,
                                                           CustomerStore.Column.ID.toString() ) + "\n"
          + ");";

    private static final String INSERT_STMT = "INSERT INTO "
                                              + DomainStore.addQuotes( TABLE_NAME )
                                              + " ( "
                                              + COLUMNS
                                              + " ) "
                                              + DomainStore.createValuesStatement( Column.values().length );

    public PaymentInfoStore() {
        // nothing to do
    }

    @Override
    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getInsertStatements( final List< PaymentInfo > paymentInfos ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final PaymentInfo paymentInfo : paymentInfos ) {
            final String insertStmt = String.format( INSERT_STMT,
                                                     toDdl( paymentInfo.getId() ),
                                                     toDdl( paymentInfo.getCustomerId() ),
                                                     toDdl( paymentInfo.getCreditCardNumber() ),
                                                     toDdl( paymentInfo.getCreditCardType().name() ),
                                                     toDdl( paymentInfo.getExpirationDate() ),
                                                     toDdl( paymentInfo.getSecurityCode() ) );
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

    @Override
    public String getTableName() {
        return PaymentInfoStore.TABLE_NAME;
    }

}

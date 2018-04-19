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

import com.redhat.hotelbooking.datagen.domain.Payment;

public final class PaymentStore implements DomainStore {

    public enum Column {

        AMOUNT,
        ID,
        PAYMENT_INFO_ID,
        RESERVATION_ID;

        public String toCreateStatement() {
            final StringBuilder builder = new StringBuilder();
            builder.append( DomainStore.addQuotes( this.toString() ) ).append( " " );

            switch ( this ) {
                case AMOUNT:
                    builder.append( "DECIMAL(8,2) NOT NULL" );
                    break;
                case ID:
                    builder.append( "INTEGER NOT NULL" );
                    break;
                case PAYMENT_INFO_ID:
                    builder.append( "INTEGER NOT NULL" );
                    break;
                case RESERVATION_ID:
                    builder.append( "INTEGER NOT NULL" );
                    break;
                default:
                    throw new RuntimeException();
            }

            return builder.toString();
        }

    }

    public static final String TABLE_NAME = "PAYMENT";
    public static final String PAYMENT_INFO_FK_NAME = "PAYMENT_INFO_FK";
    public static final String RESERVATION_FK_NAME = "RESERVATION_FK";

    private static final String COLUMNS = DomainStore.toColumnsStatement( Column.ID.name(),
                                                                          Column.RESERVATION_ID.name(),
                                                                          Column.PAYMENT_INFO_ID.name(),
                                                                          Column.AMOUNT.name() );

    private static final String CREATE_TABLE_STMT
        = "CREATE TABLE " + DomainStore.addQuotes( TABLE_NAME ) + " (\n"
          + "\t" + Column.ID.toCreateStatement() + ",\n"
          + "\t" + Column.RESERVATION_ID.toCreateStatement() + ",\n"
          + "\t" + Column.PAYMENT_INFO_ID.toCreateStatement() + ",\n"
          + "\t" + Column.AMOUNT.toCreateStatement() + ",\n"
          + "\tPRIMARY KEY ( " + DomainStore.addQuotes( Column.ID.name() ) + " ),\n"
          + "\t" + DomainStore.createForeignKeyConstraint( RESERVATION_FK_NAME,
                                                           Column.RESERVATION_ID.toString(),
                                                           ReservationStore.TABLE_NAME,
                                                           ReservationStore.Column.ID.toString() ) + ",\n"
          + "\t" + DomainStore.createForeignKeyConstraint( PAYMENT_INFO_FK_NAME,
                                                           Column.PAYMENT_INFO_ID.toString(),
                                                           PaymentInfoStore.TABLE_NAME,
                                                           PaymentInfoStore.Column.ID.toString() ) + "\n"
          + ");";

    private static final String INSERT_STMT = "INSERT INTO "
                                              + DomainStore.addQuotes( TABLE_NAME )
                                              + " ( "
                                              + COLUMNS
                                              + " ) "
                                              + DomainStore.createValuesStatement( Column.values().length );

    public PaymentStore() {
        // nothing to do
    }

    @Override
    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getInsertStatements( final List< Payment > payments ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final Payment payment : payments ) {
            final String insertStmt = String.format( INSERT_STMT,
                                                     toDdl( payment.getId() ),
                                                     toDdl( payment.getReservationId() ),
                                                     toDdl( payment.getPaymentInfoId() ),
                                                     toDdl( payment.getAmount() ) );
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

    @Override
    public String getTableName() {
        return PaymentStore.TABLE_NAME;
    }

}

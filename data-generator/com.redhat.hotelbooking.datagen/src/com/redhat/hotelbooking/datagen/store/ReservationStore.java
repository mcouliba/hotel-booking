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

import com.redhat.hotelbooking.datagen.domain.Reservation;

public final class ReservationStore implements DomainStore {

    public enum Column {

        CHECKIN,
        CHECKOUT,
        CUSTOMER_ID,
        DAILY_RATE,
        ID,
        ROOM_ID,
        STATUS;

        public String getCreateStatementTypeDefinition() {
            switch ( this ) {
                case CHECKIN:
                    return "DATE NOT NULL";
                case CHECKOUT:
                    return "DATE NOT NULL";
                case CUSTOMER_ID:
                    return CustomerStore.Column.ID.getCreateStatementTypeDefinition();
                case DAILY_RATE:
                    return "DECIMAL(7,2) NOT NULL";
                case ID:
                    return "CHARACTER VARYING(256) NOT NULL";
                case ROOM_ID:
                    return RoomStore.Column.ID.getCreateStatementTypeDefinition();
                case STATUS:
                    return "CHARACTER VARYING(256) NOT NULL";
                default:
                    throw new RuntimeException();
            }
        }

        public String toCreateStatement() {
            return DomainStore.addQuotes( toString() ) + " " + getCreateStatementTypeDefinition();
        }

    }

    public static final String TABLE_NAME = "RESERVATION";
    public static final String CUSTOMER_FK_NAME = "CUSTOMER_FK";
    public static final String ROOM_FK_NAME = "ROOM_FK";

    private static final String COLUMNS = DomainStore.toColumnsStatement( Column.ID.name(),
                                                                          Column.CUSTOMER_ID.name(),
                                                                          Column.ROOM_ID.name(),
                                                                          Column.CHECKIN.name(),
                                                                          Column.CHECKOUT.name(),
                                                                          Column.DAILY_RATE.name(),
                                                                          Column.STATUS.name() );

    private static final String CREATE_TABLE_STMT
        = "CREATE TABLE " + DomainStore.addQuotes( TABLE_NAME )+ " (\n"
          + "\t" + Column.ID.toCreateStatement() + ",\n"
          + "\t" + Column.CUSTOMER_ID.toCreateStatement() + ",\n"
          + "\t" + Column.ROOM_ID.toCreateStatement() + ",\n"
          + "\t" + Column.CHECKIN.toCreateStatement() + ",\n"
          + "\t" + Column.CHECKOUT.toCreateStatement() + ",\n"
          + "\t" + Column.DAILY_RATE.toCreateStatement() + ",\n"
          + "\t" + Column.STATUS.toCreateStatement() + ",\n"
          + "\tPRIMARY KEY ( " + DomainStore.addQuotes( Column.ID.name() ) + " ),\n"
          + "\t" + DomainStore.createForeignKeyConstraint( CUSTOMER_FK_NAME,
                                                           Column.CUSTOMER_ID.toString(),
                                                           CustomerStore.TABLE_NAME,
                                                           CustomerStore.Column.ID.toString() ) + ",\n"
          + "\t" + DomainStore.createForeignKeyConstraint( ROOM_FK_NAME,
                                                           Column.ROOM_ID.toString(),
                                                           RoomStore.TABLE_NAME,
                                                           RoomStore.Column.ID.toString() ) + "\n"
          + ");";

    private static final String INSERT_STMT = "INSERT INTO "
                                              + DomainStore.addQuotes( TABLE_NAME )
                                              + " ( "
                                              + COLUMNS
                                              + " ) "
                                              + DomainStore.createValuesStatement( Column.values().length );

    public ReservationStore() {
        // nothing to do
    }

    @Override
    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getInsertStatements( final List< Reservation > reservations ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final Reservation reservation : reservations ) {
            final String insertStmt = String.format( INSERT_STMT,
                                                     toDdl( reservation.getId() ),
                                                     toDdl( reservation.getCustomerId() ),
                                                     toDdl( reservation.getRoomId() ),
                                                     toDdl( reservation.getCheckin() ),
                                                     toDdl( reservation.getCheckout() ),
                                                     toDdl( reservation.getDailyRate() ),
                                                     toDdl( reservation.getStatus().name() ) );
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

    @Override
    public String getTableName() {
        return ReservationStore.TABLE_NAME;
    }

}

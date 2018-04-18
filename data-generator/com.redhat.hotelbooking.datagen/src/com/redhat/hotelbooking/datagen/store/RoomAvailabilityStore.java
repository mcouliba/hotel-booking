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

import com.redhat.hotelbooking.datagen.domain.RoomAvailability;

public final class RoomAvailabilityStore implements DomainStore {

    public enum Column {

        AVAILABLE,
        ID,
        RELEVANT_DATE,
        ROOM_ID;

        public String toCreateStatement() {
            final StringBuilder builder = new StringBuilder();
            builder.append( DomainStore.addQuotes( this.toString() ) ).append( " " );

            switch ( this ) {
                case AVAILABLE:
                    builder.append( "BOOLEAN NOT NULL" );
                    break;
                case ID:
                    builder.append( "INTEGER NOT NULL" );
                    break;
                case RELEVANT_DATE:
                    builder.append( "DATE NOT NULL" );
                    break;
                case ROOM_ID:
                    builder.append( "INTEGER NOT NULL" );
                    break;
                default:
                    throw new RuntimeException();
            }

            return builder.toString();
        }

    }

    public static final String TABLE_NAME = "ROOM_AVAILABILITY";
    public static final String ROOM_FK_NAME = "ROOM_FK";

    private static final String COLUMNS = DomainStore.toColumnsStatement( Column.ID.name(),
                                                                          Column.ROOM_ID.name(),
                                                                          Column.RELEVANT_DATE.name(),
                                                                          Column.AVAILABLE.name() );

    private static final String CREATE_TABLE_STMT
        = "CREATE TABLE " + DomainStore.addQuotes( TABLE_NAME ) + " (\n"
        + "\t" + Column.ID.toCreateStatement() + ",\n"
        + "\t" + Column.ROOM_ID.toCreateStatement() + ",\n"
        + "\t" + Column.RELEVANT_DATE.toCreateStatement() + ",\n"
        + "\t" + Column.AVAILABLE.toCreateStatement() + ",\n"
        + "\tPRIMARY KEY ( " + DomainStore.addQuotes( Column.ID.name() ) + " ),\n"
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

    public RoomAvailabilityStore() {
        // nothing to do
    }

    @Override
    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getInsertStatements( final List< RoomAvailability > roomAvailabilities ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final RoomAvailability roomAvailability : roomAvailabilities ) {
            final String insertStmt = String.format( INSERT_STMT,
                                                     toDdl( roomAvailability.getId() ),
                                                     toDdl( roomAvailability.getRoomId() ),
                                                     toDdl( roomAvailability.getDate() ),
                                                     toDdl( roomAvailability.isAvailable() ) );
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

    @Override
    public String getTableName() {
        return RoomAvailabilityStore.TABLE_NAME;
    }

}

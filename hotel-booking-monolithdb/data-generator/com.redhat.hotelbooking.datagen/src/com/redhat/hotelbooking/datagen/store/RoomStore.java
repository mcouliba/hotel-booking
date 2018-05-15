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

import com.redhat.hotelbooking.datagen.domain.Room;

public final class RoomStore implements DomainStore {

    public enum Column {

        FLOOR,
        HOTEL_ID,
        ID,
        RATE,
        ROOM_CONFIG_ID,
        ROOM_NUMBER;

        public String getCreateStatementTypeDefinition() {
            switch ( this ) {
                case FLOOR:
                    return "INTEGER NOT NULL";
                case HOTEL_ID:
                    return HotelStore.Column.ID.getCreateStatementTypeDefinition();
                case ID:
                    return "INTEGER NOT NULL";
                case RATE:
                    return "DECIMAL(7,2) NOT NULL";
                case ROOM_CONFIG_ID:
                    return RoomConfigStore.Column.ID.getCreateStatementTypeDefinition();
                case ROOM_NUMBER:
                    return "CHARACTER VARYING(256) NOT NULL";
                default:
                    throw new RuntimeException();
            }
        }

        public String toCreateStatement() {
            return DomainStore.addQuotes( toString() ) + " " + getCreateStatementTypeDefinition();
        }

    }

    public static final String TABLE_NAME = "ROOM";
    public static final String HOTEL_FK_NAME = "HOTEL_FK";
    public static final String ROOM_CONFIG_FK_NAME = "ROOM_CONFIG_FK";

    private static final String COLUMNS = DomainStore.toColumnsStatement( Column.ID.name(),
                                                                          Column.HOTEL_ID.name(),
                                                                          Column.ROOM_CONFIG_ID.name(),
                                                                          Column.ROOM_NUMBER.name(),
                                                                          Column.FLOOR.name(),
                                                                          Column.RATE.name() );

    private static final String CREATE_TABLE_STMT
        = "CREATE TABLE " + DomainStore.addQuotes( TABLE_NAME ) + " (\n"
          + "\t" + Column.ID.toCreateStatement() + ",\n"
          + "\t" + Column.HOTEL_ID.toCreateStatement() + ",\n"
          + "\t" + Column.ROOM_CONFIG_ID.toCreateStatement() + ",\n"
          + "\t" + Column.ROOM_NUMBER.toCreateStatement() + ",\n"
          + "\t" + Column.FLOOR.toCreateStatement() + ",\n"
          + "\t" + Column.RATE.toCreateStatement() + ",\n"
          + "\tPRIMARY KEY ( " + DomainStore.addQuotes( Column.ID.name() ) + " ),\n"
          + "\t" + DomainStore.createForeignKeyConstraint( HOTEL_FK_NAME,
                                                           Column.HOTEL_ID.toString(),
                                                           HotelStore.TABLE_NAME,
                                                           HotelStore.Column.ID.toString() ) + ",\n"
          + "\t" + DomainStore.createForeignKeyConstraint( ROOM_CONFIG_FK_NAME,
                                                           Column.ROOM_CONFIG_ID.toString(),
                                                           RoomConfigStore.TABLE_NAME,
                                                           RoomConfigStore.Column.ID.toString() ) + "\n"
          + ");";

    private static final String INSERT_STMT = "INSERT INTO "
                                              + DomainStore.addQuotes( TABLE_NAME )
                                              + " ( "
                                              + COLUMNS
                                              + " ) "
                                              + DomainStore.createValuesStatement( Column.values().length );

    public RoomStore() {
        // nothing to do
    }

    @Override
    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getInsertStatements( final List< Room > rooms ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final Room room : rooms ) {
            final String insertStmt = String.format( INSERT_STMT,
                                                     toDdl( room.getId() ),
                                                     toDdl( room.getHotelId() ),
                                                     toDdl( room.getConfig() ),
                                                     toDdl( room.getRoomNumber() ),
                                                     toDdl( room.getFloor() ),
                                                     toDdl( room.getRate() ) );
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

}

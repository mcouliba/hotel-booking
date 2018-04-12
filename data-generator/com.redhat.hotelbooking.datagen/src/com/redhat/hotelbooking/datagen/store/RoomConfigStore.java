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

import com.redhat.hotelbooking.datagen.domain.RoomConfig;

public final class RoomConfigStore implements DomainStore {

    public enum Column {

        ID,
        LIVING_AREA,
        MICROWAVE,
        NUM_ADJOINING_ROOMS,
        NUM_DOUBLE_BEDS,
        NUM_KING_BEDS,
        NUM_PETS,
        NUM_PULLOUTS,
        NUM_QUEEN_BEDS,
        REFRIGERATOR,
        SMOKING

    }

    public static final String TABLE_NAME = "ROOM_CONFIG";

    // @formatter:off

    private static final String COLUMNS = "\"" + Column.ID + "\", "
                                          + '"' + Column.LIVING_AREA + "\", "
                                          + '"' + Column.MICROWAVE + "\", "
                                          + '"' + Column.NUM_ADJOINING_ROOMS + "\", "
                                          + '"' + Column.NUM_DOUBLE_BEDS + "\", "
                                          + '"' + Column.NUM_KING_BEDS + "\", "
                                          + '"' + Column.NUM_PETS + "\", "
                                          + '"' + Column.NUM_PULLOUTS + "\", "
                                          + '"' + Column.NUM_QUEEN_BEDS + "\", "
                                          + '"' + Column.REFRIGERATOR + "\", "
                                          + '"' + Column.SMOKING + '\"';

    private static final String CREATE_TABLE_STMT = "CREATE TABLE \"" + TABLE_NAME + "\" (\n"
                                                    + "\t\"" + Column.ID + "\" INTEGER NOT NULL,\n"
                                                    + "\t\"" + Column.LIVING_AREA + "\" BOOLEAN NOT NULL,\n"
                                                    + "\t\"" + Column.MICROWAVE + "\" BOOLEAN NOT NULL,\n"
                                                    + "\t\"" + Column.NUM_ADJOINING_ROOMS + "\" INTEGER NOT NULL,\n"
                                                    + "\t\"" + Column.NUM_DOUBLE_BEDS + "\" INTEGER NOT NULL,\n"
                                                    + "\t\"" + Column.NUM_KING_BEDS + "\" INTEGER NOT NULL,\n"
                                                    + "\t\"" + Column.NUM_PETS + "\" INTEGER NOT NULL,\n"
                                                    + "\t\"" + Column.NUM_PULLOUTS + "\" INTEGER NOT NULL,\n"
                                                    + "\t\"" + Column.NUM_QUEEN_BEDS + "\" INTEGER NOT NULL,\n"
                                                    + "\t\"" + Column.REFRIGERATOR + "\" BOOLEAN NOT NULL,\n"
                                                    + "\t\"" + Column.SMOKING + "\" BOOLEAN NOT NULL,\n"
                                                    + "\tPRIMARY KEY ( \"" + Column.ID + "\" )\n"
                                                    + ");";

    private static final String INSERT_STMT = "INSERT INTO \""
                                              + TABLE_NAME
                                              + "\" ( "
                                              + COLUMNS
                                              + " ) "
                                              + DomainStore.createValuesStatement( Column.values().length );

    // @formatter:on

    public RoomConfigStore() {
        // nothing to do
    }

    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getDropStatement() {
        return getDropStatement( TABLE_NAME );
    }

    public String getInsertStatements( final List< RoomConfig > roomConfigs ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final RoomConfig roomConfig : roomConfigs ) {
            // @formatter:off
            final String insertStmt = String.format( INSERT_STMT,
                                                     toDdl( roomConfig.getId() ),
                                                     toDdl( roomConfig.hasLivingSpace() ),
                                                     toDdl( roomConfig.hasMicrowave() ),
                                                     toDdl( roomConfig.getNumAdjoiningRooms() ),
                                                     toDdl( roomConfig.getNumDouble() ),
                                                     toDdl( roomConfig.getNumKing() ),
                                                     toDdl( roomConfig.getNumPets() ),
                                                     toDdl( roomConfig.getNumPullouts() ),
                                                     toDdl( roomConfig.getNumQueen() ),
                                                     toDdl( roomConfig.hasRefrigerator() ),
                                                     toDdl( roomConfig.isSmoking() ) );
            // @formatter:on
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

}

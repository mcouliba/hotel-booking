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
        SMOKING;

        public String getCreateStatementTypeDefinition() {
            switch ( this ) {
                case ID:
                    return "INTEGER NOT NULL";
                case LIVING_AREA:
                    return "BOOLEAN NOT NULL";
                case MICROWAVE:
                    return "BOOLEAN NOT NULL";
                case NUM_ADJOINING_ROOMS:
                    return "INTEGER NOT NULL";
                case NUM_DOUBLE_BEDS:
                    return "INTEGER NOT NULL";
                case NUM_KING_BEDS:
                    return "INTEGER NOT NULL";
                case NUM_PETS:
                    return "INTEGER NOT NULL";
                case NUM_PULLOUTS:
                    return "INTEGER NOT NULL";
                case NUM_QUEEN_BEDS:
                    return "INTEGER NOT NULL";
                case REFRIGERATOR:
                    return "BOOLEAN NOT NULL";
                case SMOKING:
                    return "BOOLEAN NOT NULL";
                default:
                    throw new RuntimeException();
            }
        }

        public String toCreateStatement() {
            return DomainStore.addQuotes( toString() ) + " " + getCreateStatementTypeDefinition();
        }

    }

    public static final String TABLE_NAME = "ROOM_CONFIG";

    private static final String COLUMNS = DomainStore.toColumnsStatement( Column.ID.name(),
                                                                          Column.LIVING_AREA.name(),
                                                                          Column.MICROWAVE.name(),
                                                                          Column.NUM_ADJOINING_ROOMS.name(),
                                                                          Column.NUM_DOUBLE_BEDS.name(),
                                                                          Column.NUM_KING_BEDS.name(),
                                                                          Column.NUM_PETS.name(),
                                                                          Column.NUM_PULLOUTS.name(),
                                                                          Column.NUM_QUEEN_BEDS.name(),
                                                                          Column.REFRIGERATOR.name(),
                                                                          Column.SMOKING.name() );

    private static final String CREATE_TABLE_STMT = "CREATE TABLE " + DomainStore.addQuotes( TABLE_NAME ) + " (\n"
                                                    + "\t" + Column.ID.toCreateStatement() + ",\n"
                                                    + "\t" + Column.LIVING_AREA.toCreateStatement() + ",\n"
                                                    + "\t" + Column.MICROWAVE.toCreateStatement() + ",\n"
                                                    + "\t" + Column.NUM_ADJOINING_ROOMS.toCreateStatement() + ",\n"
                                                    + "\t" + Column.NUM_DOUBLE_BEDS.toCreateStatement() + ",\n"
                                                    + "\t" + Column.NUM_KING_BEDS.toCreateStatement() + ",\n"
                                                    + "\t" + Column.NUM_PETS.toCreateStatement() + ",\n"
                                                    + "\t" + Column.NUM_PULLOUTS.toCreateStatement() + ",\n"
                                                    + "\t" + Column.NUM_QUEEN_BEDS.toCreateStatement() + ",\n"
                                                    + "\t" + Column.REFRIGERATOR.toCreateStatement() + ",\n"
                                                    + "\t" + Column.SMOKING.toCreateStatement() + ",\n"
                                                    + "\tPRIMARY KEY ( " + DomainStore.addQuotes( Column.ID.name() ) + " )\n"
                                                    + ");";

    private static final String INSERT_STMT = "INSERT INTO "
                                              + DomainStore.addQuotes( TABLE_NAME )
                                              + " ( "
                                              + COLUMNS
                                              + " ) "
                                              + DomainStore.createValuesStatement( Column.values().length );

    public RoomConfigStore() {
        // nothing to do
    }

    @Override
    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getInsertStatements( final List< RoomConfig > roomConfigs ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final RoomConfig roomConfig : roomConfigs ) {
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
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

    @Override
    public String getTableName() {
        return RoomConfigStore.TABLE_NAME;
    }

}

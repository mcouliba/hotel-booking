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

import com.redhat.hotelbooking.datagen.domain.City;

public final class CityStore implements DomainStore {

    public enum Column {

        COUNTRY_ID,
        ID,
        NAME,
        POSTAL_CODE;

        public String toCreateStatement() {
            final StringBuilder builder = new StringBuilder();
            builder.append( DomainStore.addQuotes( this.toString() ) ).append( " " );

            switch ( this ) {
                case COUNTRY_ID:
                    builder.append( "INTEGER NOT NULL" );
                    break;
                case ID:
                    builder.append( "INTEGER NOT NULL" );
                    break;
                case NAME:
                    builder.append( "CHARACTER VARYING(256) NOT NULL" );
                    break;
                case POSTAL_CODE:
                    builder.append( "CHARACTER VARYING(256) NOT NULL" );
                    break;
                default:
                    throw new RuntimeException();
            }

            return builder.toString();
        }

    }

    public static final String TABLE_NAME = "CITY";
    public static final String COUNTRY_FK_NAME = "COUNTRY_FK";

    private static final String COLUMNS = DomainStore.toColumnsStatement( Column.ID.name(),
                                                                          Column.COUNTRY_ID.name(),
                                                                          Column.NAME.name(),
                                                                          Column.POSTAL_CODE.name() );

    private static final String CREATE_TABLE_STMT
        = "CREATE TABLE " + DomainStore.addQuotes( TABLE_NAME ) + " (\n"
          + "\t" + Column.ID.toCreateStatement() + ",\n"
          + "\t" + Column.COUNTRY_ID.toCreateStatement() + ",\n"
          + "\t" + Column.NAME.toCreateStatement() + ",\n"
          + "\t" + Column.POSTAL_CODE.toCreateStatement() + ",\n"
          + "\tPRIMARY KEY ( " + DomainStore.addQuotes( Column.ID .name() ) + " ),\n"
          + "\t" + DomainStore.createForeignKeyConstraint( COUNTRY_FK_NAME,
                                                           Column.COUNTRY_ID.toString(),
                                                           CountryStore.TABLE_NAME,
                                                           CountryStore.Column.ID.toString() ) + "\n"
          + ");";

    private static final String INSERT_STMT = "INSERT INTO "
                                              + DomainStore.addQuotes( TABLE_NAME )
                                              + " ( "
                                              + COLUMNS
                                              + " ) "
                                              + DomainStore.createValuesStatement( Column.values().length );

    public CityStore() {
        // nothing to do
    }

    @Override
    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getInsertStatements( final List< City > cities ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final City city : cities ) {
            final String insertStmt = String.format( INSERT_STMT,
                                                     toDdl( city.getId() ),
                                                     toDdl( city.getCountryId() ),
                                                     toDdl( city.getName() ),
                                                     toDdl( city.getPostalCode() ) );
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

    @Override
    public String getTableName() {
        return CityStore.TABLE_NAME;
    }

}

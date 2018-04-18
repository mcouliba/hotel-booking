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

import com.redhat.hotelbooking.datagen.domain.Customer;

public final class CustomerStore implements DomainStore {

    public enum Column {

        ADDRESS_LINE_1,
        CITY,
        EMAIL,
        ID,
        MEMBER_SINCE,
        NAME,
        PASSWORD,
        POSTAL_CODE,
        REWARDS_ID;

        public String toCreateStatement() {
            final StringBuilder builder = new StringBuilder();
            builder.append( DomainStore.addQuotes( this.toString() ) ).append( " " );

            switch ( this ) {
                case ADDRESS_LINE_1:
                    builder.append( "CHARACTER VARYING(256) NOT NULL" );
                    break;
                case CITY:
                    builder.append( "CHARACTER VARYING(256) NOT NULL" );
                    break;
                case EMAIL:
                    builder.append( "CHARACTER VARYING(256) NOT NULL" );
                    break;
                case ID:
                    builder.append( "INTEGER NOT NULL" );
                    break;
                case MEMBER_SINCE:
                    builder.append( "DATE NOT NULL" );
                    break;
                case NAME:
                    builder.append( "CHARACTER VARYING(256) NOT NULL" );
                    break;
                case PASSWORD:
                    builder.append( "CHARACTER VARYING(256) NOT NULL" );
                    break;
                case POSTAL_CODE:
                    builder.append( "CHARACTER VARYING(256) NOT NULL" );
                    break;
                case REWARDS_ID:
                    builder.append( "CHARACTER VARYING(256) NOT NULL" );
                    break;
                default:
                    throw new RuntimeException();
            }

            return builder.toString();
        }

    }

    public static final String TABLE_NAME = "CUSTOMER";

    private static final String COLUMNS = DomainStore.toColumnsStatement( Column.ID.name(),
                                                                          Column.NAME.name(),
                                                                          Column.PASSWORD.name(),
                                                                          Column.EMAIL.name(),
                                                                          Column.REWARDS_ID.name(),
                                                                          Column.MEMBER_SINCE.name(),
                                                                          Column.ADDRESS_LINE_1.name(),
                                                                          Column.CITY.name(),
                                                                          Column.POSTAL_CODE.name() );

    private static final String CREATE_TABLE_STMT = "CREATE TABLE " + DomainStore.addQuotes( TABLE_NAME ) + " (\n"
                                                    + "\t" + Column.ID.toCreateStatement() + ",\n"
                                                    + "\t" + Column.NAME.toCreateStatement() + ",\n"
                                                    + "\t" + Column.PASSWORD.toCreateStatement() + ",\n"
                                                    + "\t" + Column.EMAIL.toCreateStatement() + ",\n"
                                                    + "\t" + Column.REWARDS_ID.toCreateStatement() + ",\n"
                                                    + "\t" + Column.MEMBER_SINCE.toCreateStatement() + ",\n"
                                                    + "\t" + Column.ADDRESS_LINE_1.toCreateStatement() + ",\n"
                                                    + "\t" + Column.CITY.toCreateStatement() + ",\n"
                                                    + "\t" + Column.POSTAL_CODE.toCreateStatement() + ",\n"
                                                    + "\tPRIMARY KEY ( " + DomainStore.addQuotes( Column.ID.name() ) + " )\n"
                                                    + ");";

    private static final String INSERT_STMT = "INSERT INTO "
                                              + DomainStore.addQuotes( TABLE_NAME )
                                              + " ( "
                                              + COLUMNS
                                              + " ) "
                                              + DomainStore.createValuesStatement( Column.values().length );

    public CustomerStore() {
        // nothing to do
    }

    @Override
    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getInsertStatements( final List< Customer > customers ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final Customer customer : customers ) {
            final String insertStmt = String.format( INSERT_STMT,
                                                     toDdl( customer.getId() ),
                                                     toDdl( customer.getName() ),
                                                     toDdl( customer.getPassword() ),
                                                     toDdl( customer.getEmail() ),
                                                     toDdl( customer.getRewardsId() ),
                                                     toDdl( customer.getMemberSince() ),
                                                     toDdl( customer.getAddressLine1() ),
                                                     toDdl( customer.getCity() ),
                                                     toDdl( customer.getPostalCode() ) );
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

    @Override
    public String getTableName() {
        return CustomerStore.TABLE_NAME;
    }

}

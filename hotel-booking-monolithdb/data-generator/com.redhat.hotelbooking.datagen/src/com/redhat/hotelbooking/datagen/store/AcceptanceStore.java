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

import com.redhat.hotelbooking.datagen.domain.Acceptance;

public final class AcceptanceStore implements DomainStore {

    public enum Column {

        CUSTOMER_ID,
        STATEMENT_1,
        STATEMENT_2,
        STATEMENT_3;

        public String getCreateStatementTypeDefinition() {
            switch ( this ) {
                case CUSTOMER_ID:
                    return CustomerStore.Column.ID.getCreateStatementTypeDefinition();
                case STATEMENT_1:
                    return "BOOLEAN NOT NULL";
                case STATEMENT_2:
                    return "BOOLEAN NOT NULL";
                case STATEMENT_3:
                    return "BOOLEAN NOT NULL";
                default:
                    throw new RuntimeException();
            }
        }

        public String toCreateStatement() {
            return DomainStore.addQuotes( toString() ) + " " + getCreateStatementTypeDefinition();
        }

    }

    public static final String TABLE_NAME = "ACCEPTANCE";
    public static final String CUSTOMER_FK_NAME = "CUSTOMER_FK";

    private static final String COLUMNS = DomainStore.toColumnsStatement( Column.CUSTOMER_ID.name(),
                                                                          Column.STATEMENT_1.name(),
                                                                          Column.STATEMENT_2.name(),
                                                                          Column.STATEMENT_3.name() );

    private static final String CREATE_TABLE_STMT
        = "CREATE TABLE " + DomainStore.addQuotes( TABLE_NAME ) + " (\n"
          + "\t" + Column.CUSTOMER_ID.toCreateStatement() + ",\n"
          + "\t" + Column.STATEMENT_1.toCreateStatement() + ",\n"
          + "\t" + Column.STATEMENT_2.toCreateStatement() + ",\n"
          + "\t" + Column.STATEMENT_3.toCreateStatement() + ",\n"
          + "\tPRIMARY KEY ( " + DomainStore.addQuotes( Column.CUSTOMER_ID .name() ) + " ),\n"
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

    public AcceptanceStore() {
        // nothing to do
    }

    @Override
    public String getCreateTableStatement() {
        return CREATE_TABLE_STMT;
    }

    public String getInsertStatements( final List< Acceptance > acceptances ) {
        final StringBuilder ddl = new StringBuilder();
        ddl.append( "\n--" ).append( TABLE_NAME ).append( "\n\n" );

        for ( final Acceptance acceptance : acceptances ) {
            final String insertStmt = String.format( INSERT_STMT,
                                                     toDdl( acceptance.getCustomer_id() ),
                                                     toDdl( acceptance.isStatement1Accepted() ),
                                                     toDdl( acceptance.isStatement2Accepted() ),
                                                     toDdl( acceptance.isStatement3Accepted() ) );
            ddl.append( insertStmt ).append( '\n' );
        }

        return ddl.toString();
    }

    @Override
    public String getTableName() {
        return AcceptanceStore.TABLE_NAME;
    }

}

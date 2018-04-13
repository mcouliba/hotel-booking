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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public interface DomainStore {

    SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
    String DROP_TABLE_STMT = "DROP TABLE IF EXISTS \"%s\" CASCADE;";

    static String createForeignKeyConstraint( final String foreignKeyName,
                                              final String keyColumnName,
                                              final String referencedTableName,
                                              final String referencedColumnName ) {
        // @formatter:off
        return "CONSTRAINT \""
                + foreignKeyName
                + "\" FOREIGN KEY ( \""
                + keyColumnName
                + "\" ) REFERENCES \""
                + referencedTableName
                + "\" ( \""
                + referencedColumnName
                + "\" )";
        // // @formatter:on
    }

    static String createValuesStatement( final int numColumns ) {
        final StringBuilder builder = new StringBuilder( "VALUES ( " );
        boolean firstTime = true;

        for ( int i = 0; i < numColumns; ++i ) {
            if ( firstTime ) {
                firstTime = false;
            } else {
                builder.append( ", " );
            }

            builder.append( "%s" );
        }

        builder.append( " );" );
        return builder.toString();
    }

    String getCreateTableStatement();

    String getDropStatement();

    default String getDropStatement( final String tableName ) {
        return String.format( DROP_TABLE_STMT, tableName );
    }

    default String toDdl( final Object value ) {
        if ( value == null ) {
            throw new RuntimeException( "Unable to convert null to DDL value" );
        }

        if ( value instanceof Timestamp ) {
            return "'" + DATE_FORMATTER.format( ( ( Timestamp ) value ).getTime() ) + "'";
        }

        if ( !( value instanceof String ) ) {
            return value.toString();
        }

        // escape any empbedded single quotes
        return "'" + ( ( String ) value ).replace( "'", "''" ) + "'";
    }

}

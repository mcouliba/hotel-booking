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
package com.redhat.hotelbooking.datagen.domain;

import java.util.Objects;

public final class State implements Comparable< State > {

    private final String abbreviation;
    private final String name;

    public State( final String name,
                  final String abbreviation ) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    @Override
    public int compareTo( final State that ) {
        return this.abbreviation.compareTo( that.abbreviation );
    }

    @Override
    public boolean equals( final Object obj ) {
        if ( obj == null || !getClass().equals( obj.getClass() ) ) {
            return false;
        }

        final State that = ( State ) obj;
        return Objects.equals( this.abbreviation, that.abbreviation ) && Objects.equals( this.name, that.name );
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.abbreviation, this.name );
    }

    @Override
    public String toString() {
        return "State: name = " + this.name + ", abbreviation = " + this.abbreviation;
    }

}
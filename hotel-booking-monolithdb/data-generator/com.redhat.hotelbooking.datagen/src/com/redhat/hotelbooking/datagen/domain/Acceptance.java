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

public final class Acceptance {

    private final int customer_id;
    private final boolean statement1;
    private final boolean statement2;
    private final boolean statement3;

    public Acceptance( final int customerId,
                       final boolean statement1,
                       final boolean statement2,
                       final boolean statement3 ) {
        this.customer_id = customerId;
        this.statement1 = statement1;
        this.statement2 = statement2;
        this.statement3 = statement3;
    }

    public int getCustomer_id() {
        return this.customer_id;
    }

    public boolean isStatement1Accepted() {
        return this.statement1;
    }

    public boolean isStatement2Accepted() {
        return this.statement2;
    }

    public boolean isStatement3Accepted() {
        return this.statement3;
    }

}

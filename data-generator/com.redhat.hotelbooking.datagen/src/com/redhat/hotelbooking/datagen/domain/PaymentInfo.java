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

import java.util.Date;

public final class PaymentInfo {

    private final int creditCardNumber;
    private final String creditCardType;
    private final int customerId;
    private final Date expirationDate;
    private final int id;
    private final int securityCode;

    public PaymentInfo( final int id,
                        final int customerId,
                        final int creditCardNumber,
                        final String creditCardType,
                        final Date expirationDate,
                        final int securityCode ) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardType = creditCardType;
        this.customerId = customerId;
        this.expirationDate = expirationDate;
        this.id = id;
        this.securityCode = securityCode;
    }

    public int getCreditCardNumber() {
        return this.creditCardNumber;
    }

    public String getCreditCardType() {
        return this.creditCardType;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public int getId() {
        return this.id;
    }

    public int getSecurityCode() {
        return this.securityCode;
    }

}

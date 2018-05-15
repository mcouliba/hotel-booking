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

public final class Payment {

    private final double amount;
    private final int id;
    private final int paymentInfoId;
    private final String reservationId;

    public Payment( final int id,
                    final int paymentInfoId,
                    final String reservationId,
                    final double amount ) {
        this.amount = amount;
        this.id = id;
        this.paymentInfoId = paymentInfoId;
        this.reservationId = reservationId;
    }

    public double getAmount() {
        return this.amount;
    }

    public int getId() {
        return this.id;
    }

    public int getPaymentInfoId() {
        return this.paymentInfoId;
    }

    public String getReservationId() {
        return this.reservationId;
    }

}

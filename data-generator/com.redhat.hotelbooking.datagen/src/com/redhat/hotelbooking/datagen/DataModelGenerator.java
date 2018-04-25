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
package com.redhat.hotelbooking.datagen;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.redhat.hotelbooking.datagen.DataProvider.Settings;
import com.redhat.hotelbooking.datagen.domain.Acceptance;
import com.redhat.hotelbooking.datagen.domain.City;
import com.redhat.hotelbooking.datagen.domain.Country;
import com.redhat.hotelbooking.datagen.domain.Customer;
import com.redhat.hotelbooking.datagen.domain.Hotel;
import com.redhat.hotelbooking.datagen.domain.HotelChain;
import com.redhat.hotelbooking.datagen.domain.Payment;
import com.redhat.hotelbooking.datagen.domain.PaymentInfo;
import com.redhat.hotelbooking.datagen.domain.Reservation;
import com.redhat.hotelbooking.datagen.domain.Room;
import com.redhat.hotelbooking.datagen.domain.RoomAvailability;
import com.redhat.hotelbooking.datagen.domain.RoomConfig;
import com.redhat.hotelbooking.datagen.store.AcceptanceStore;
import com.redhat.hotelbooking.datagen.store.CityStore;
import com.redhat.hotelbooking.datagen.store.CountryStore;
import com.redhat.hotelbooking.datagen.store.CustomerStore;
import com.redhat.hotelbooking.datagen.store.DomainStore;
import com.redhat.hotelbooking.datagen.store.HotelChainStore;
import com.redhat.hotelbooking.datagen.store.HotelStore;
import com.redhat.hotelbooking.datagen.store.PaymentInfoStore;
import com.redhat.hotelbooking.datagen.store.PaymentStore;
import com.redhat.hotelbooking.datagen.store.ReservationStore;
import com.redhat.hotelbooking.datagen.store.RoomAvailabilityStore;
import com.redhat.hotelbooking.datagen.store.RoomConfigStore;
import com.redhat.hotelbooking.datagen.store.RoomStore;

public final class DataModelGenerator {

    public static final String OUTPUT_FILE_NAME = "resources/generated/hotel-booking.ddl";

    public static void main( final String[] args ) {
        final Settings settings = new Settings(); // settings that control the number and values of rows generated

        // values which eventually could be changed by args
        final boolean verbose = false;
        final boolean generateDropStatements = true;
        final boolean generateCreateTableStatements = true;
        final boolean generateInsertStatements = true;
        final String outputFileName = OUTPUT_FILE_NAME;

        final DataModelGenerator generator = new DataModelGenerator( verbose, settings );

        try {
            generator.generate( generateDropStatements,
                                generateCreateTableStatements,
                                generateInsertStatements,
                                outputFileName );
        } catch ( final Exception error ) {
            System.err.println( error.getLocalizedMessage() );
        }
    }

    private final AcceptanceStore acceptanceTable;
    private final CityStore cityTable;
    private final CountryStore countryTable;
    private final CustomerStore customerTable;
    private final HotelChainStore hotelChainTable;
    private final HotelStore hotelTable;
    private final PaymentInfoStore paymentInfoTable;
    private final ReservationStore reservationTable;
    private final RoomAvailabilityStore roomAvailabilityTable;
    private final RoomConfigStore roomConfigTable;
    private final RoomStore roomTable;
    private final PaymentStore paymentTable;

    private final List< DomainStore > createTableOrdering = new ArrayList<>();
    private final DataProvider dataProvider;
    private final List< DomainStore > dropTableOrdering;
    private final boolean verbose;

    private DataModelGenerator( final boolean verbose,
                                final Settings settings ) {
        this.verbose = verbose;
        this.dataProvider = new DataProvider( settings );

        // initially put in collection in the order the tables need to be created
        this.countryTable = new CountryStore();
        this.createTableOrdering.add( this.countryTable );

        this.cityTable = new CityStore();
        this.createTableOrdering.add(  this.cityTable );

        this.customerTable = new CustomerStore();
        this.createTableOrdering.add( this.customerTable );

        this.paymentInfoTable = new PaymentInfoStore();
        this.createTableOrdering.add( this.paymentInfoTable );

        this.acceptanceTable = new AcceptanceStore();
        this.createTableOrdering.add( this.acceptanceTable );

        this.hotelChainTable = new HotelChainStore();
        this.createTableOrdering.add( this.hotelChainTable );

        this.hotelTable = new HotelStore();
        this.createTableOrdering.add( this.hotelTable );

        this.roomConfigTable = new RoomConfigStore();
        this.createTableOrdering.add( this.roomConfigTable );

        this.roomTable = new RoomStore();
        this.createTableOrdering.add( this.roomTable );

        this.roomAvailabilityTable = new RoomAvailabilityStore();
        this.createTableOrdering.add( this.roomAvailabilityTable );

        this.reservationTable = new ReservationStore();
        this.createTableOrdering.add( this.reservationTable );

        this.paymentTable = new PaymentStore();
        this.createTableOrdering.add( this.paymentTable );

        // reverse order for dropping tables
        this.dropTableOrdering = new ArrayList<>( this.createTableOrdering );
        Collections.reverse( this.dropTableOrdering );
    }

    private void generate( final boolean generateDropTables,
                           final boolean generateCreateTableStatements,
                           final boolean generateInsertStatements,
                           final String outputFileName ) throws Exception {
        final StringBuilder builder = new StringBuilder();

        if ( generateDropTables ) {
            generateDropStatements( builder );
            builder.append( "\n" );
        }

        if ( generateCreateTableStatements ) {
            generateCreateTableStatements( builder );
            builder.append( "\n" );
        }

        if ( generateInsertStatements ) {
            generateInsertStatements( builder );
        }

        if ( this.verbose ) {
            System.out.println( "\nDDL:\n" + builder.toString() + "\n" );
        }

        writeOutputFile( builder, outputFileName );
    }

    private void generateCreateTableStatements( final StringBuilder builder ) {
        final long start = System.currentTimeMillis();

        if ( this.verbose ) {
            System.out.print( "Generate create table statements... " );
        }

        for ( final DomainStore table : this.createTableOrdering ) {
            builder.append( table.getCreateTableStatement() ).append( "\n\n" );
        }

        if ( this.verbose ) {
            System.out.println( "done (" + ( System.currentTimeMillis() - start ) + "ms)" );
        }
    }

    private void generateDropStatements( final StringBuilder builder ) {
        final long start = System.currentTimeMillis();

        if ( this.verbose ) {
            System.out.print( "Generate drop statements... " );
        }

        for ( final DomainStore table : this.dropTableOrdering ) {
            builder.append( DomainStore.getDropStatement( table.getTableName() ) ).append( "\n" );
        }

        if ( this.verbose ) {
            System.out.println( "done (" + ( System.currentTimeMillis() - start ) + "ms)" );
        }
    }

    private void generateInsertStatements( final StringBuilder builder ) throws Exception {
        final long start = System.currentTimeMillis();

        if ( this.verbose ) {
            System.out.print( "Generate insert statements... " );
        }

        final List< Country > countries = this.dataProvider.generateCountries();
        builder.append( this.countryTable.getInsertStatements( countries ) );

        final List< City > cities = this.dataProvider.generateCities();
        builder.append( this.cityTable.getInsertStatements( cities ) );

        final List< Customer > customers = this.dataProvider.generateCustomers();
        builder.append( this.customerTable.getInsertStatements( customers ) );

        final List< PaymentInfo > paymentInfos = this.dataProvider.generatePaymentInfos( customers );
        builder.append( this.paymentInfoTable.getInsertStatements( paymentInfos ) );

        final List< Acceptance > acceptances = this.dataProvider.generateAcceptances( customers );
        builder.append( this.acceptanceTable.getInsertStatements( acceptances ) );

        final List< HotelChain > hotelChains = this.dataProvider.generateHotelChains();
        builder.append( this.hotelChainTable.getInsertStatements( hotelChains ) );

        final List< Hotel > hotels = this.dataProvider.generateHotels( hotelChains, cities );
        builder.append( this.hotelTable.getInsertStatements( hotels ) );

        final List< RoomConfig > roomConfigs = this.dataProvider.generateRoomConfigs();
        builder.append( this.roomConfigTable.getInsertStatements( roomConfigs ) );

        final List< Room > rooms = this.dataProvider.generateRooms( hotels, roomConfigs );
        builder.append( this.roomTable.getInsertStatements( rooms ) );

        final List< RoomAvailability > roomAvailabilities = this.dataProvider.generateAvailabilities( rooms );
        builder.append( this.roomAvailabilityTable.getInsertStatements( roomAvailabilities ) );

        final List< Reservation > reservations = this.dataProvider.generateReservations( customers, rooms );
        builder.append( this.reservationTable.getInsertStatements( reservations ) );

        final List< Payment > payments = this.dataProvider.generatePayments( reservations, paymentInfos );
        builder.append( this.paymentTable.getInsertStatements( payments ) );

        if ( this.verbose ) {
            System.out.println( "done (" + ( System.currentTimeMillis() - start ) + "ms)" );
        }
    }

    private void writeOutputFile( final StringBuilder builder,
                                  final String outputFileName ) throws Exception {
        if ( outputFileName == null ) {
            throw new RuntimeException( "Output file name is null" );
        }

        final long start = System.currentTimeMillis();

        if ( this.verbose ) {
            System.out.print( "Writing DDL file '" + outputFileName + "'... " );
        }

        final Path output = Paths.get( outputFileName );

        // write file
        Files.write( output, builder.toString().getBytes() );

        if ( this.verbose ) {
            System.out.println( "done (" + ( System.currentTimeMillis() - start ) + "ms)" );
        }
    }

}

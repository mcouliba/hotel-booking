package com.redhat.hotelbooking.datagen;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.redhat.hotelbooking.datagen.store.CountryStore;
import com.redhat.hotelbooking.datagen.store.CustomerStore;
import com.redhat.hotelbooking.datagen.store.DomainStore;
import com.redhat.hotelbooking.datagen.store.HotelChainStore;
import com.redhat.hotelbooking.datagen.store.HotelStore;
import com.redhat.hotelbooking.datagen.store.PaymentInfoStore;
import com.redhat.hotelbooking.datagen.store.ReservationStore;
import com.redhat.hotelbooking.datagen.store.RoomAvailabilityStore;
import com.redhat.hotelbooking.datagen.store.RoomConfigStore;
import com.redhat.hotelbooking.datagen.store.RoomStore;
import com.redhat.hotelbooking.datagen.store.TransactionStore;

public final class DataModelGenerator {

    public static final String OUTPUT_FILE_NAME = "resources/generated/hotel-booking.ddl";

    public static void main( final String[] args ) {
        // values which eventually be changed by args
        final boolean verbose = true;
        final boolean generateDropStatements = true;
        final boolean generateCreateTableStatements = true;
        final String outputFileName = OUTPUT_FILE_NAME;

        final DataModelGenerator generator = new DataModelGenerator( verbose );

        try {
            generator.generate( generateDropStatements, generateCreateTableStatements, outputFileName );
        } catch ( final Exception error ) {
            System.err.println( error.getLocalizedMessage() );
        }
    }

    private final CountryStore countryTable;
    private final CustomerStore customerTable;
    private final HotelChainStore hotelChainTable;
    private final HotelStore hotelTable;
    private final PaymentInfoStore paymentInfoTable;
    private final ReservationStore reservationTable;
    private final RoomAvailabilityStore roomAvailabilityTable;
    private final RoomConfigStore roomConfigTable;
    private final RoomStore roomTable;
    private final TransactionStore transactionTable;

    private final List< DomainStore > createTableOrdering = new ArrayList<>();
    private final List< DomainStore > dropTableOrdering;
    private final boolean verbose;

    private DataModelGenerator( final boolean verbose ) {
        this.verbose = verbose;

        // initially put in collection in the order the tables need to be dropped
        this.countryTable = new CountryStore();
        this.createTableOrdering.add( this.countryTable );

        this.customerTable = new CustomerStore();
        this.createTableOrdering.add( this.customerTable );

        this.paymentInfoTable = new PaymentInfoStore();
        this.createTableOrdering.add( this.paymentInfoTable );

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

        this.transactionTable = new TransactionStore();
        this.createTableOrdering.add( this.transactionTable );

        // reverse order for dropping tables
        this.dropTableOrdering = new ArrayList<>( this.createTableOrdering );
        Collections.reverse( this.dropTableOrdering );
    }

    private void generate( final boolean generateDropTables,
                           final boolean generateCreateTableStatements,
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
            builder.append( table.getDropStatement() ).append( "\n" );
        }

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

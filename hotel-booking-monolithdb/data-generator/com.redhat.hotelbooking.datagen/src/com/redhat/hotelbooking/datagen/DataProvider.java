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

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.redhat.hotelbooking.datagen.domain.Acceptance;
import com.redhat.hotelbooking.datagen.domain.City;
import com.redhat.hotelbooking.datagen.domain.Country;
import com.redhat.hotelbooking.datagen.domain.Customer;
import com.redhat.hotelbooking.datagen.domain.Hotel;
import com.redhat.hotelbooking.datagen.domain.HotelChain;
import com.redhat.hotelbooking.datagen.domain.Payment;
import com.redhat.hotelbooking.datagen.domain.PaymentInfo;
import com.redhat.hotelbooking.datagen.domain.PaymentInfo.CreditCardType;
import com.redhat.hotelbooking.datagen.domain.Reservation;
import com.redhat.hotelbooking.datagen.domain.Room;
import com.redhat.hotelbooking.datagen.domain.RoomAvailability;
import com.redhat.hotelbooking.datagen.domain.RoomConfig;
import com.redhat.hotelbooking.datagen.store.DomainStore;
import com.redhat.hotelbooking.datagen.util.RandomGenerator;

public final class DataProvider {

    public static class Settings {

        public boolean generateRoomNotAvailableRecord = false;
        public LocalDate firstReservationDate = LocalDate.now();
        public int maxNumHotelsPerCityToGenerate = 8;
        public int minNumHotelsPerCityToGenerate = 4;
        public int numDaysAvailabilityPerRoom = 60;
        public int numCustomersToGenerate = 50;
        public int numReservationsToGenerate = 100;
        public int numRoomConfigsToGenerate = 100;
        public int numRoomsPerHotel = 50;
        public LocalDate oldestMembershipDate = LocalDate.now().minusYears( 10 );

        // The following are used to create the database record IDs.
        // hotel chain ID = 1
        // country IDs 101-110
        // city IDs 200-232
        public int roomAvailabilityStartId = 8000;
        public int customerStartId = 400;
        public int hotelStartId = 800;
        public int paymentInfoStartId = 1000;
        public int reservationStartId = 2000;
        public int roomConfigStartId = 700;
        public int roomStartId = 3000;
        public int paymentStartId = 7000;

    }

    private static List< String > _femaleNames;
    private static List< String > _lastNames;
    private static List< String > _maleNames;
    private static List< String > _streets;
    private static List< String > _streetSuffixes;

    private static String[] EMAIL_PROVIDERS = {
        "aol.com",
        "gmail.com",
        "icloud.com",
        "mail.com",
        "outlook.com",
        "yahoo.com",
        "zoho.com"
    };

    private static final String RESOURCES_FOLDER = "resources/";
    private static final String FEMALE_NAMES_FILE = RESOURCES_FOLDER + "female_names.txt";
    private static final String LAST_NAMES_FILE = RESOURCES_FOLDER + "last_names.txt";
    private static final String MALE_NAMES_FILE = RESOURCES_FOLDER + "male_names.txt";
    private static final String STREET_SUFFIXES_FILE = RESOURCES_FOLDER + "street_suffixes.txt";
    private static final String STREETS_FILE = RESOURCES_FOLDER + "streets.txt";

    private static List< String > getFemaleNames() throws Exception {
        if ( _femaleNames == null ) {
            _femaleNames = DomainStore.load( FEMALE_NAMES_FILE );
        }

        return _femaleNames;
    }

    private static List< String > getLastNames() throws Exception {
        if ( _lastNames == null ) {
            _lastNames = DomainStore.load( LAST_NAMES_FILE );
        }

        return _lastNames;
    }

    private static List< String > getMaleNames() throws Exception {
        if ( _maleNames == null ) {
            _maleNames = DomainStore.load( MALE_NAMES_FILE );
        }

        return _maleNames;
    }

    private static List< String > getStreets() throws Exception {
        if ( _streets == null ) {
            _streets = DomainStore.load( STREETS_FILE );
        }

        return _streets;
    }

    private static List< String > getStreetSuffixes() throws Exception {
        if ( _streetSuffixes == null ) {
            _streetSuffixes = DomainStore.load( STREET_SUFFIXES_FILE );
        }

        return _streetSuffixes;
    }

    private final Settings settings;
    private final RandomGenerator random = new RandomGenerator();
    private final List< String > usedAddresses = new ArrayList<>();
    private final List< String > usedCreditCards = new ArrayList<>();
    private final List< String > usedEmails = new ArrayList<>();
    private final List< String > usedNames = new ArrayList<>();
    private final List< String > usedRewardIds = new ArrayList<>();

    public DataProvider( final Settings settings ) {
        this.settings = settings == null ? new Settings() : settings;
    }

    public List< Acceptance > generateAcceptances( final List< Customer > customers ) {
        final List< Acceptance > acceptances = new ArrayList<>( customers.size() );

        for ( final Customer customer: customers ) {
            final City city = City.find( customer.getCityId() );
            final Country country = Country.find( city.getCountryId() );
            final boolean accept = !Country.isEuMember( country );
            final Acceptance acceptance = new Acceptance( customer.getId(), accept, accept, accept );
            acceptances.add( acceptance );
        }

        return acceptances;
    }

    public List< RoomAvailability > generateAvailabilities( final List< Room > rooms ) {
        final int total = rooms.size() * this.settings.numDaysAvailabilityPerRoom;
        final List< RoomAvailability > availabilities = new ArrayList<>( total );

        for ( final Room room : rooms ) {
            for ( int i = 0; i < this.settings.numDaysAvailabilityPerRoom; ++i ) {
                final LocalDate day = this.settings.firstReservationDate.plusDays( i );
                final RoomAvailability availability = new RoomAvailability( this.settings.roomAvailabilityStartId,
                                                                            room.getId(),
                                                                            this.random.next(),
                                                                            Timestamp.valueOf( day.atStartOfDay() ) );

                if ( availability.isAvailable() || this.settings.generateRoomNotAvailableRecord ) {
                    availabilities.add( availability );
                    ++this.settings.roomAvailabilityStartId;
                }
            }
        }

        return availabilities;
    }

    public List< City > generateCities() {
        return City.CITIES;
    }

    public List< Country > generateCountries() {
        return Country.COUNTRIES;
    }

    private String generateCreditCardNumber( final CreditCardType creditCardType ) {
        String creditCardNumber = null;
        int start = 0;

        switch ( creditCardType ) {
            case AMERICAN_EXPRESS:
                start = 3000;
                break;
            case DISCOVER:
                start = 6000;
                break;
            case MASTERCARD:
                start = 5000;
                break;
            case VISA:
                start = 4000;
                break;
            default:
                throw new RuntimeException();
        }

        while ( creditCardNumber == null || this.usedCreditCards.contains( creditCardNumber ) ) {
            final int firstPart = this.random.next( start, 9999 );
            final int secondPart = this.random.next( 1000, 9999 );
            final int thirdPart = this.random.next( 1000, 9999 );
            final int fourthPart = this.random.next( 1000, 9999 );
            creditCardNumber = Integer.toString( firstPart )
                                + Integer.toString( secondPart )
                                + Integer.toString( thirdPart )
                                + Integer.toString( fourthPart );
        }

        return creditCardNumber;
    }

    public List< Customer > generateCustomers() throws Exception {
        final List< Customer > customers = new ArrayList<>( this.settings.numCustomersToGenerate );

        for ( int i = 0; i < this.settings.numCustomersToGenerate; ++i ) {
            // obtain unique name
            String[] names = null;
            String name = null;

            while ( names == null || this.usedNames.contains( name ) ) {
                names = nextName();
                name = names[ 0 ] + " " + names[ 1 ];
            }

            // obtain unique email & password
            String email = null;
            String pswd = null;

            while ( email == null || this.usedEmails.contains( email ) ) {
                email = nextEmail( names[ 0 ], names[ 1 ] );
                pswd = email.substring( 0, email.indexOf( '@' ) );
            }

            // obtain unique address
            String addressLine1 = null;

            while ( addressLine1 == null || this.usedAddresses.contains( addressLine1 ) ) {
                addressLine1 = nextAddressLine1();
            }

            final City city = nextCity();

            // obtain unique rewards ID
            String rewardsId = null;

            while ( rewardsId == null || this.usedRewardIds.contains( rewardsId ) ) {
                rewardsId = nextRewardsId();
            }

            final Customer customer = new Customer( i + this.settings.customerStartId,
                                                    name,
                                                    email,
                                                    pswd,
                                                    addressLine1,
                                                    city.getId(),
                                                    Timestamp.valueOf( nextMemberSince().atStartOfDay() ),
                                                    rewardsId );
            customers.add( customer );
        }

        return customers;
    }

    public List< HotelChain > generateHotelChains() {
        return HotelChain.HOTEL_CHAINS;
    }

    public List< Hotel > generateHotels( final List< HotelChain > hotelChains,
                                         final List< City > cities ) throws Exception {
        final List< Hotel > hotels = new ArrayList<>();

        for ( final HotelChain hotelChain : hotelChains ) {
            final String url = hotelChain.getName().toLowerCase().replace( " ", "" ) + ".com";

            for ( final City city : cities ) {
                final int numHotels = this.random.next(  this.settings.minNumHotelsPerCityToGenerate,
                                                         this.settings.maxNumHotelsPerCityToGenerate );

                for ( int i = 0; i < numHotels; ++i ) {
                    // obtain unique address
                    String addressLine1 = null;

                    while ( addressLine1 == null || this.usedAddresses.contains( addressLine1 ) ) {
                        addressLine1 = nextAddressLine1();
                    }

                    final String email = city.getName().toLowerCase().replace( " ", "" ) + "-" + ( i + 1 ) + "@" + url;

                    // construct name
                    final int index = addressLine1.indexOf( ' ' ); // remove street number
                    final String hotelName = hotelChain.getName()
                                             + " - "
                                             + city.getName()
                                             + " " + addressLine1.substring( index + 1 );

                    final Hotel hotel = new Hotel( this.settings.hotelStartId++,
                                                   hotelChain.getId(),
                                                   hotelName,
                                                   addressLine1,
                                                   city.getId(),
                                                   email,
                                                   roundRate( this.random.next( 2.5f, 4.0f ) ),
                                                   url );
                    hotels.add( hotel );
                }

            }
        }

        return hotels;
    }

    public List< PaymentInfo > generatePaymentInfos( final List< Customer > customers ) {
        final List< PaymentInfo > paymentInfos = new ArrayList<>( customers.size() );
        int i = 0;

        for ( final Customer customer: customers ) {
            final CreditCardType creditCardType = this.random.next( CreditCardType.values() );
            final int securityCode = this.random.next( 100, 999 );
            final String creditCardNumber = generateCreditCardNumber( creditCardType );
            final int expirationYear = LocalDate.now().getYear() + 1;
            final LocalDate expirationDate = this.random.next( LocalDate.of( expirationYear, 1, 1 ),
                                                               LocalDate.of( expirationYear + 4, 12, 31 ) );

            final PaymentInfo paymentInfo = new PaymentInfo( i + this.settings.paymentInfoStartId,
                                                             customer.getId(),
                                                             creditCardNumber,
                                                             creditCardType,
                                                             Timestamp.valueOf( expirationDate.atStartOfDay() ),
                                                             securityCode );
            paymentInfos.add( paymentInfo );
            ++i;
        }

        return paymentInfos;
    }

    public List< Payment > generatePayments( final List< Reservation > reservations,
                                             final List< PaymentInfo > paymentInfos ) {
        final List< Payment > payments = new ArrayList<>();

        final Timestamp now = Timestamp.valueOf( LocalDate.now().atStartOfDay() );

        for ( final Reservation reservation : reservations ) {
            if ( reservation.getCheckout().before( now ) ) {
                final long numDays = ChronoUnit.DAYS.between( reservation.getCheckin().toLocalDateTime().toLocalDate(),
                                                              reservation.getCheckout().toLocalDateTime().toLocalDate() );
                final Optional< PaymentInfo > paymentInfo
                    = paymentInfos.stream().filter(
                          p -> p.getCustomerId() == reservation.getCustomerId()
                      ).findFirst();
                final Payment payment = new Payment( this.settings.paymentStartId++,
                                                     paymentInfo.get().getId(),
                                                     reservation.getId(),
                                                     roundRate( reservation.getDailyRate() * numDays ) );

                payments.add(  payment );
            }
        }

        return payments;
    }

    public List< Reservation > generateReservations( final List< Customer > customers,
                                                     final List< Room > rooms ) {
        final List< Reservation > reservations = new ArrayList<>( this.settings.numReservationsToGenerate );
        final LocalDate firstEligibleDay = LocalDate.of( 2018, 1, 1 );

        for ( int i = 0; i < this.settings.numReservationsToGenerate; ++i ) {
            final Room room = this.random.next( rooms );
            final Customer customer = this.random.next( customers );
            final LocalDate checkin = this.random.next( firstEligibleDay, LocalDate.now() );
            final int numDays = this.random.next( 2, 9 );
            final LocalDate checkout = this.random.next( checkin.plusDays( 1 ), checkin.plusDays( numDays ) );

            final Reservation reservation = new Reservation( Integer.toString( i + this.settings.reservationStartId ),
                                                             room.getId(),
                                                             customer.getId(),
                                                             Timestamp.valueOf( checkin.atStartOfDay() ),
                                                             Timestamp.valueOf( checkout.atStartOfDay() ),
                                                             room.getRate(),
                                                             Reservation.Status.RESERVED );
            reservations.add( reservation );
        }

        return reservations;
    }

    public List< RoomConfig > generateRoomConfigs() {
        final List< RoomConfig > roomConfigs = new ArrayList<>( this.settings.numRoomConfigsToGenerate );

        for ( int i = 0; i < this.settings.numRoomConfigsToGenerate; ++i ) {
            RoomConfig roomConfig = null;

            while ( roomConfig == null || roomConfigs.contains( roomConfig ) ) {
                final boolean livingSpace = this.random.next();
                final int numPullouts = livingSpace ? this.random.next( 0, 1 ) : 0;
                final boolean refrigerator = this.random.next();
                final boolean microwave = refrigerator ? this.random.next() : false;
                final boolean smoking = this.random.next();
                final int numAdjoiningRooms = this.random.next( 0, 2 );
                final int numPets = this.random.next( 0, 2 );

                // beds
                final int numKing = this.random.next( 0, 2 );
                final int numQueen = numKing == 2 ? 0 : this.random.next( 0, 2 - numKing );
                final int numDouble = 2 - numKing - numQueen;

                roomConfig = new RoomConfig( i + this.settings.roomConfigStartId,
                                             livingSpace,
                                             microwave,
                                             numAdjoiningRooms,
                                             numDouble,
                                             numKing,
                                             numPets,
                                             numPullouts,
                                             numQueen,
                                             refrigerator,
                                             smoking );
            }

            roomConfigs.add( roomConfig );
        }

        return roomConfigs;
    }

    public List< Room > generateRooms( final List< Hotel > hotels,
                                       final List< RoomConfig > roomConfigs ) {
        final List< Room > rooms = new ArrayList<>( hotels.size() * this.settings.numRoomsPerHotel );

        for ( final Hotel hotel : hotels ) {
            int floor = 1;
            int roomNumber = 1;

            for ( int i = 0; i < this.settings.numRoomsPerHotel; ++i ) {
                final RoomConfig roomConfig = this.random.next( roomConfigs );
                final double rate = roundRate( this.random.next( 75.00f, 250.00f ) );
                final String roomTitle = floor + "-" + roomNumber;
                final Room room = new Room( this.settings.roomStartId++,
                                            hotel.getId(),
                                            roomConfig.getId(),
                                            rate,
                                            floor,
                                            roomTitle );
                rooms.add( room );

                if ( i != 0 && i % 20 == 0 ) {
                    ++floor;
                    roomNumber = 1;
                } else {
                    ++roomNumber;
                }
            }
        }

        return rooms;
    }

    private String nextAddressLine1() throws Exception {
        final int number = this.random.next( 1, 900 );
        final String street = nextStreet();
        final String suffix = this.random.next( DataProvider.getStreetSuffixes() );
        return number + " " + street + ' ' + suffix;
    }

    private City nextCity() throws Exception {
        return this.random.next( City.CITIES );
    }

    private String nextEmail( final String firstName,
                              final String lastName ) {
       final String provider = this.random.next( EMAIL_PROVIDERS );
       String email = firstName.substring( 0, this.random.next( 1, firstName.length() - 1 ) );
       int i = 0;

       while ( email.length() < 6 && i < firstName.length() ) {
           email += lastName.charAt( i++ );
       }

       return email.toLowerCase() + "@" + provider;
    }

    private LocalDate nextMemberSince() {
        return this.random.next( this.settings.oldestMembershipDate, LocalDate.now() );
    }

    private String[] nextName() throws Exception {
        final String firstName =  this.random.next( this.random.next() ? DataProvider.getMaleNames()
                                                                       : DataProvider.getFemaleNames() );
        final String lastName = this.random.next( DataProvider.getLastNames() );
        return new String[] { firstName, lastName };
    }

    private String nextRewardsId() {
        final int firstPart = this.random.next( 1000, 9999 );
        final int lastPart = this.random.next( 1000, 9999 );
        return "RHH-" + firstPart + "-" + lastPart;
    }

    private String nextStreet() throws Exception {
        return this.random.next( DataProvider.getStreets() );
    }

    private double roundRate( final double rate ) {
        final double scale = Math.pow( 10, 2 );
        return Math.round( rate * scale ) / scale;
   }

}

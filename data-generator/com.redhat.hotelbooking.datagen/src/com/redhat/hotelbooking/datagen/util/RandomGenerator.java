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
package com.redhat.hotelbooking.datagen.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * A generator of random things.
 */
public class RandomGenerator {

    /**
     * Keep seed the same so that order is repeatable.
     */
    private static final long SEED = LocalDateTime.of( 2010, 1, 1, 1, 1 ).getLong( ChronoField.INSTANT_SECONDS );

    private final Random random;

    /**
     * Constructs a random generator with the default seed.
     */
    public RandomGenerator() {
        this( SEED );
    }

    /**
     * @param seed
     *            the seed used to construct the random generator
     */
    public RandomGenerator( final long seed ) {
        this.random = new Random( seed );
    }

    /**
     * @return a random <code>boolean</code>
     */
    public boolean next() {
        return this.random.nextBoolean();
    }

    /**
     * @param elements
     *            the elements being used (cannot be <code>null</code> or empty)
     * @return the random element (can be <code>null</code> if collection contains
     *         <code>null</code> elements)
     */
    public < T > T next( final Collection< T > elements ) {
        if ( Objects.requireNonNull( elements, "elements" ).isEmpty() ) {
            throw new DataGenerationException( "Cannot generate a random element of an empty collection" ); // TODO i18n
        }

        final int random = next( 0, elements.size() - 1 );
        return next( elements, random );
    }

    /**
     * @param min
     *            the <code>float</code> with the smallest value that can be
     *            returned
     * @param max
     *            the <code>float</code> with the largest value that can be returned
     * @return the random float
     */
    public float next( final float min,
                       final float max ) {
        return min + this.random.nextFloat() * ( max - min );
    }

    /**
     * @param min
     *            the <code>int</code> with the smallest value that can be returned
     * @param max
     *            the the <code>int</code> with the largest value that can be
     *            returned
     * @return the random float
     */
    public int next( final int min,
                     final int max ) {
        return this.random.nextInt( max - min + 1 ) + min;
    }

    /**
     * @param iterable
     *            a collection that can be iterated (cannot be <code>null</code> or
     *            empty)
     * @param index
     *            the index of the element to return
     * @return the element (can be <code>null</code> if the element at the specified
     *         index is <code>null</code>)
     */
    private < T > T next( final Iterable< T > iterable,
                          final int index ) {
        // make sure not null or empty
        if ( !Objects.requireNonNull( iterable, "iterable" ).iterator().hasNext() ) {
            throw new DataGenerationException( "Iterable cannot be empty" ); // TODO i18n
        }

        if ( iterable instanceof List ) {
            return ( ( List< T > ) iterable ).get( index );
        }

        int i = -1;
        T result = null;

        for ( final T elem : iterable ) {
            ++i;

            if ( i == index ) {
                result = elem;
                break;
            }
        }

        if ( result == null ) {
            throw new DataGenerationException( "Collection does not have element at index" ); // TODO i18n
        }

        return result;
    }

    /**
     * @param min
     *            the <code>LocalDateTime</code> with the smallest value that can be
     *            returned (cannot be <code>null</code>)
     * @param max
     *            the the <code>LocalDateTime</code> with the largest value that can
     *            be returned (cannot be <code>null</code>)
     * @return the random date/time (never <code>null</code>)
     */
    public LocalDateTime next( final LocalDateTime min,
                               final LocalDateTime max ) {
        final long first = Objects.requireNonNull( min, "min" ).getLong( ChronoField.INSTANT_SECONDS );
        final long last = Objects.requireNonNull( max, "max" ).getLong( ChronoField.INSTANT_SECONDS );
        long diff;

        if ( first >= 0 ) {
            diff = last - first + 1;
        } else if ( last >= 0 ) {
            diff = Math.abs( first ) + last + 1;
        } else {
            diff = Math.abs( first ) - Math.abs( last ) + 1;
        }

        return LocalDateTime.ofEpochSecond( first + ( long ) ( this.random.nextDouble() * diff ), 0, ZoneOffset.UTC );
    }

    /**
     * @param enums
     *            the enum array whose random element is being generated (cannot be
     *            <code>null</code> or empty)
     * @return the random enum (cannot be <code>null</code> or empty)
     */
    public < T extends Enum< T > > T next( final T[] enums ) {
        if ( Objects.requireNonNull( enums, "enums" ).length == 0 ) {
            throw new DataGenerationException( "Cannot generate a random enum of an empty enum array" ); // TODO i18n
        }

        final int index = next( 0, enums.length - 1 );
        return enums[index];
    }

    /**
     * @param elements
     *            the array whose random element is being generated (cannot be
     *            <code>null</code> or empty)
     * @return the random array element (can be <code>null</code> or empty if array
     *         element is <code>null</code> or empty)
     */
    public < T > T next( final T[] elements ) {
        if ( Objects.requireNonNull( elements, "elements" ).length == 0 ) {
            throw new DataGenerationException( "Cannot generate a random element of an empty array" ); // TODO i18n
        }

        final int index = next( 0, elements.length - 1 );
        return elements[index];
    }

}

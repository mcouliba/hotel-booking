package com.redhat.hotelbooking.repository;


import com.redhat.hotelbooking.bean.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Integer> {
    Page<Reservation> findByCustomerId(Pageable pageable, Integer customerid);
}